package com.digiteo.neovoteII.service;

import com.digiteo.neovoteII.mapstruct.dtos.AdminDTO;
import com.digiteo.neovoteII.mapstruct.dtos.AdminPatchDTO;

public interface AdminService {

    void addNewAdmin(AdminDTO aDTO);
    void partialUpdateAdminWithMapper(Long adminId, AdminPatchDTO aDTO);
    void deleteAdmin(Long adminId);
}
