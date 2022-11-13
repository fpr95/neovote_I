package com.digiteo.neovoteII.mapstruct.mappers;

import com.digiteo.neovoteII.mapstruct.dtos.AdminDTO;
import com.digiteo.neovoteII.mapstruct.dtos.AdminPatchDTO;
import com.digiteo.neovoteII.model.Admin;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface AdminMapper {

    @Mapping(source = "name", target = "a_name")
    @Mapping(source = "lastname", target = "a_lastName")
    @Mapping(source = "userName", target = "a_userName")
    @Mapping(source = "key", target = "a_key")
    @Mapping(source = "genderCode", target = "gender")
    @Mapping(source = "rut", target = "a_rut")
    @Mapping(source = "phone", target = "a_phone")
    @Mapping(source = "email", target = "a_email")
    Admin toEntity(AdminDTO dto);

    //check the @GetMapping handler in controller
    @InheritInverseConfiguration(name = "toEntity")
    AdminDTO toDto(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "userName", target = "a_userName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "key", target = "a_key", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "a_phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "a_email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        // in case some Admin wants to change the 'enabled' field it would be necessary to add a @Mapping for that value
    void partialUpdateFromDto(AdminPatchDTO dto, @MappingTarget Admin entity);
}
