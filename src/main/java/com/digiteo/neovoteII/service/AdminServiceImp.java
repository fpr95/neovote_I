package com.digiteo.neovoteII.service;

import com.digiteo.neovoteII.mapstruct.dtos.AdminDTO;
import com.digiteo.neovoteII.mapstruct.dtos.AdminPatchDTO;
import com.digiteo.neovoteII.mapstruct.mappers.AdminMapper;
import com.digiteo.neovoteII.model.Admin;
import com.digiteo.neovoteII.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService{

    private final AdminRepository adminRepository;
    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImp(AdminRepository adminRepository, AdminMapper adminMapper){
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    // ----------------------------------------------------------------------------------------------------------------o
    @Override
    @Transactional
    public void addNewAdmin(AdminDTO aDTO) {
        Optional<Admin> adminOptional = adminRepository.findAdminByUserName(aDTO.getUserName());
        if(adminOptional.isPresent()) {
            throw new IllegalStateException(
                    "Este nombre de usuario ya está asociado a un administrador en el sistema, por favor eliga otro."); }

        adminOptional = adminRepository.findAdminByRut(aDTO.getRut());
        if(adminOptional.isPresent()) {
            throw new IllegalStateException(
                    "Este rut ya está asociado a un administrador en el sistema."); }

        adminOptional = adminRepository.findAdminByPhone(aDTO.getPhone());
        if(adminOptional.isPresent()) {
            throw new IllegalStateException("Este número ya está asociado a un administrador en el sistema, eliga otro."); }

        adminOptional = adminRepository.findAdminByEmail(aDTO.getEmail());
        if(adminOptional.isPresent()) {
            throw new IllegalStateException("Este correo ya está asociado a un votante en el sistema, eliga otro."); }

        adminRepository.save(adminMapper.toEntity(aDTO));
    }

    // ----------------------------------------------------------------------------------------------------------------o
    @Override
    @Transactional
    public void partialUpdateAdminWithMapper(Long adminId, AdminPatchDTO aPatchDTO) {
        Admin a = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un administrador con el ID otorgado"));
        adminMapper.partialUpdateFromDto(aPatchDTO, a);
        adminRepository.save(a);
    }

    // ----------------------------------------------------------------------------------------------------------------o
    @Override
    public void deleteAdmin(Long adminId) {
        Admin a = adminRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un administrador con el ID otorgado"));
        adminRepository.delete(a);
    }
}
