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
    @Mapping(source = "key", target = "v_key")
    @Mapping(source = "phone", target = "v_phone")
    @Mapping(source = "email", target = "v_email")
    Voter toEntity(VoterDTO dto);

    //check impl of this method if any problem with the @GetMapping handler in controller
    @InheritInverseConfiguration(name = "toEntity")
    VoterDTO toDto(Voter voter);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "v_name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastname", target = "v_lastname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "key", target = "v_key", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "v_phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "v_email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        //@Mapping(source = "enabled", target = "v_enabled", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(VoterDTO dto,@MappingTarget Voter entity);
    //@MappingTarget allow to update an existing entity, in this case, Voter.

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "name", target = "v_name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastname", target = "v_lastname", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "key", target = "v_key", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "v_phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "v_email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdateFromDto(VoterPatchDTO dto,@MappingTarget Voter entity);

    @Mapping(source = "v_name", target = "name")
    @Mapping(source = "v_lastname", target = "lastname")
    List<VoterGetDTO> entitiesToDTOs(List<Voter> voters);

    //	VoterGetDTO voterToVoterGetDTO(Voter entity, @MappingTarget VoterGetDTO dto);

}

