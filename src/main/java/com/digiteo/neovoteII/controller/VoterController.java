package com.digiteo.neovoteII.controller;

// import java.util.HashMap;
import java.util.List;
// import java.util.Map;

import com.digiteo.neovoteII.mapstruct.dtos.VoterDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterPatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestParam;

import com.digiteo.neovoteII.service.VoterServiceImp;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v2/voter")
@Validated
public class VoterController {

    private final VoterServiceImp voterServiceImp;
    // Find a way to inject this collection for looser coupling (avoid 'new' keyword)
//	private HashMap<String, Object> response;

    @Autowired
    public VoterController(VoterServiceImp voterServiceImp) {
        this.voterServiceImp = voterServiceImp;
    }

//	@Autowired
//	public VoterController(VoterService voterService,HashMap<String,Object> response) {
//		this.voterService = voterService;
//		this.response = response;
//	}

    @GetMapping
    public ResponseEntity<List<VoterGetDTO>> getVoterGetDTOs(){
        return new ResponseEntity<>(voterServiceImp.getVoterGetDTOs(), HttpStatus.OK);
    }

    //In case of using DTO pattern for register a new voter, modify this method to receive a VoterDTO
    @PostMapping
    public ResponseEntity<?> registerNewVoter(@Valid @RequestBody VoterDTO vDTO) {
        voterServiceImp.addNewVoter(vDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("El usuario " + vDTO.getName() + " ha sido registrado exitosamente.");
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateVoterWithMapper(
            @PathVariable Long id,@Valid @RequestBody VoterPatchDTO vPatchDTO) {
        voterServiceImp.partialUpdateVoterWithMapper(id, vPatchDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Sus datos se han modificado exitosamente");
    }

    @DeleteMapping(path = "{voterId}")
    public ResponseEntity<?> deleteVoter(@PathVariable("voterId") Long id) {
        voterServiceImp.deleteVoter(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Su perfil se ha eliminado del sistema");
    }


}
