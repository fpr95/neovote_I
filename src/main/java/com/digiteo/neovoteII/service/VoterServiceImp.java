package com.digiteo.neovoteII.service;

import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Optional;

import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterPatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digiteo.neovoteII.mapstruct.dtos.VoterDTO;
import com.digiteo.neovoteII.mapstruct.mappers.VoterMapper;
import com.digiteo.neovoteII.model.Voter;
import com.digiteo.neovoteII.repository.VoterRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class VoterServiceImp implements VoterService{

    private final VoterRepository voterRepository;
    private VoterMapper voterMapper;

    @Autowired
    public VoterServiceImp(VoterRepository voterRepository, VoterMapper voterMapper) {
        this.voterRepository = voterRepository;
        this.voterMapper = voterMapper;
    }
    // -----------------------------------------------------------------------------------------------------------------------------------o
    // Get all voters (VoterGetDTOs), these will be sent with the result of the election when status change to "FINISHED". (HTTP GET)
    // Improvement 2add: Change initializer of 'getDTOs' to LinkedList and use the index integers as return data in controller method
    public List<VoterGetDTO> getVoterGetDTOs () {
        List<VoterGetDTO> getDTOs;
        return getDTOs = new ArrayList<>(voterMapper.entitiesToGetDTOs(voterRepository.findAll()));
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------o
    // To register new voter (HTTP POST)
    @Transactional
    public void addNewVoter(VoterDTO vDTO) {
        Optional<Voter> voterOptional = voterRepository.findVoterByUserName(vDTO.getUserName());
        if(voterOptional.isPresent()) {
            throw new IllegalStateException(
                    "Este nombre de usuario ya está asociado a un votante en el sistema, por favor eliga otro."); }

        voterOptional = voterRepository.findVoterByRut(vDTO.getRut());
        if(voterOptional.isPresent()) {
            throw new IllegalStateException(
                    "Este rut ya está asociado a un votante en el sistema, si el RUT que ingresó es el suyo, dirigase con el administrador."); }

        voterOptional = voterRepository.findVoterByPhone(vDTO.getPhone());
        if(voterOptional.isPresent()) {
            throw new IllegalStateException("Este número ya está asociado a un votante en el sistema, eliga otro."); }

        voterOptional = voterRepository.findVoterByEmail(vDTO.getEmail());
        if(voterOptional.isPresent()) {
            throw new IllegalStateException("Este correo ya está asociado a un votante en el sistema, eliga otro."); }

        voterRepository.save(voterMapper.toEntity(vDTO));
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------o
    // Partial update for an existing entity mapping fields from DTO
    @Transactional
    public void partialUpdateVoterWithMapper(Long voterId, VoterPatchDTO vPatchDTO) {
        Voter v = voterRepository.findById(voterId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un votante con el ID otorgado"));
        voterMapper.partialUpdateFromDto(vPatchDTO, v);
        voterRepository.save(v);
    }
    // ---------------------------------------------------------------------------------------------------------------------o
    // To delete a Voter entity in DDBB
    public void deleteVoter(Long voterId) {
        Voter v = voterRepository.findById(voterId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un votante con el ID otorgado"));
        voterRepository.delete(v);
    }
}
