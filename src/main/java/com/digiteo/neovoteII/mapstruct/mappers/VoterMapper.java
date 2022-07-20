package com.digiteo.neovoteII.mapstruct.mappers;

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
    List<VoterGetDTO> entitiesToDTOs(List<Voter> voters);

    //	VoterGetDTO voterToVoterGetDTO(Voter entity, @MappingTarget VoterGetDTO dto);

}

