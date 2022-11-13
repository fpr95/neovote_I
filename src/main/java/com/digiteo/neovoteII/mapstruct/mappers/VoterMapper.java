package com.digiteo.neovoteII.mapstruct.mappers;

import com.digiteo.neovoteII.mapstruct.dtos.VoterPatchDTO;
import org.mapstruct.*;

import com.digiteo.neovoteII.mapstruct.dtos.VoterDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;
import com.digiteo.neovoteII.model.Voter;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface VoterMapper {

    @Mapping(source = "name", target = "v_name")
    @Mapping(source = "lastname", target = "v_lastname")
    @Mapping(source = "userName", target = "v_userName")
    @Mapping(source = "key", target = "v_key")
    @Mapping(source = "genderCode", target = "gender")
    @Mapping(source = "rut", target = "v_rut")
    @Mapping(source = "phone", target = "v_phone")
    @Mapping(source = "email", target = "v_email")
    Voter toEntity(VoterDTO dto);

    //check the @GetMapping handler in controller
    @InheritInverseConfiguration(name = "toEntity")
    VoterDTO toDto(Voter voter);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "v_name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastname", target = "v_lastname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userName", target = "v_userName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "key", target = "v_key", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "genderCode", target = "gender", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "rut", target = "v_rut", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "v_phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "v_email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // in case some Admin wants to change the 'enabled' field it would be necessary to add a @Mapping for that value
    void fullUpdateFromDto(VoterDTO dto,@MappingTarget Voter entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //@Mapping(source = "name", target = "v_name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //@Mapping(source = "lastname", target = "v_lastname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userName", target = "v_userName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "key", target = "v_key", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //@Mapping(source = "genderCode", target = "gender", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //@Mapping(source = "rut", target = "v_rut", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "v_phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "v_email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // in case some Admin wants to change the 'enabled' field it would be necessary to add a @Mapping for that value
    void partialUpdateFromDto(VoterPatchDTO dto,@MappingTarget Voter entity);

    @Mapping(source = "v_name", target = "name")
    @Mapping(source = "v_lastname", target = "lastname")
    List<VoterGetDTO> entitiesToGetDTOs(List<Voter> voters);

    @Mapping(source = "v_name", target = "name")
    @Mapping(source = "v_lastname", target = "lastname")
    VoterGetDTO voterToVoterGetDTO(Voter entity);

}

