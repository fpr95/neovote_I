package com.digiteo.neovoteII.controller;

import com.digiteo.neovoteII.mapstruct.dtos.AdminDTO;
import com.digiteo.neovoteII.mapstruct.dtos.AdminPatchDTO;
import com.digiteo.neovoteII.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v2/admin")
@Validated
public class AdminController {

    private final AdminServiceImp adminServiceImp;

    @Autowired
    public AdminController(AdminServiceImp adminServiceImp){ this.adminServiceImp = adminServiceImp; }

    @PostMapping
    public ResponseEntity<?> registerNewAdmin(@Valid @RequestBody AdminDTO aDTO) {
        adminServiceImp.addNewAdmin(aDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("El administrador " + aDTO.getName() + " ha sido registrado exitosamente.");
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateAdminWithMapper(
            @PathVariable Long id, @Valid @RequestBody AdminPatchDTO aPatchDTO) {
        adminServiceImp.partialUpdateAdminWithMapper(id, aPatchDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Sus datos se han modificado exitosamente");
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("adminId") Long id) {
        adminServiceImp.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Su perfil se ha eliminado del sistema");
    }
}
