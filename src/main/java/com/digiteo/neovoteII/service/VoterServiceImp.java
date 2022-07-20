package com.digiteo.neovoteII.service;

import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Optional;

import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;
//import com.digiteo.neovoteII.mapstruct.dtos.VoterPostDTO;
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
    //Now using the regexp attribute in @Pattern annotation for validation [18/07/22]
    //private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    //Now using the regexp attribute in @Pattern annotation for validation [18/07/22]
    //private static final String PHONE_REGEX = "^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$";

    @Autowired
    public VoterServiceImp(VoterRepository voterRepository, VoterMapper voterMapper) {
        this.voterRepository = voterRepository;
        this.voterMapper = voterMapper;
    }
    // --------------------------------------------------------------------------------------------------------------------o
    // Validate email with REGEX -> Now using the regexp attribute in @Pattern annotation for validation [18/07/22]
    //public static boolean isEmailValid(String email) {
    //    final Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    //    return emailPattern.matcher(email).matches();
    //}
    // --------------------------------------------------------------------------------------------------------------------o
    // Validate phone number with REGEX -> Now using the regexp attribute in @Pattern annotation for validation [18/07/22]
    //public static boolean isPhoneValid(String phone) {
    //    final Pattern phonePattern = Pattern.compile(PHONE_REGEX);
    //    return phonePattern.matcher(phone).matches();
    //}
    // -----------------------------------------------------------------------------------------------------------------------------------o
    // Get all voters (VoterGetDTOs) in DDBB, these will be sent with the result of the election. (HTTP GET)
    // Improvement 2add: Change initializer of 'getDTOs' to LinkedList and use the index integers as return data in controller method
    public List<VoterGetDTO> getVoterGetDTOs () {
        List<VoterGetDTO> getDTOs;
        return getDTOs = new ArrayList<>(voterMapper.entitiesToDTOs(voterRepository.findAll()));
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------o
    // To register new voter (HTTP POST). Restrictions/Validations for v_name/v_lastname and v_key need to be added.
    // Improvement 2add: Change to VoterDTO the parameter and map it at the end just besides JPA's save method - Change controller method to adapt
    @Transactional
    public void addNewVoter(VoterDTO vDTO) {
        //if(!isPhoneValid(voter.getV_phone())) { throw new IllegalStateException("Debe ingresar un número de contacto válido"); }

        Optional<Voter> voterOptional = voterRepository.findVoterByPhone(vDTO.getPhone());

        if(voterOptional.isPresent()) { throw new IllegalStateException("Este número ya está asociado a un votante en el sistema, eliga otro."); }

        //if(!isEmailValid(voter.getV_email())) { throw new IllegalStateException("Debe ingresar un correo válido"); }

        voterOptional = voterRepository.findVoterByEmail(vDTO.getEmail());

        if(voterOptional.isPresent()) { throw new IllegalStateException("Este correo ya está asociado a un votante en el sistema, eliga otro."); }

        voterRepository.save(voterMapper.toEntity(vDTO));
    }
    // --------------------------------------------------------------------------------------------------------------------o
    // To achieve partial update for an existing entity using Mapstruct
    @Transactional
    public void partialUpdateVoterWithMapper(Long voterId,VoterDTO vDTO) {
        Voter v = voterRepository.findById(voterId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un votante con el ID otorgado"));
        // After testing, check feasibility to inject 'vDTO' in constructor of the current service class,
        // or add a dto object as parameter for looser coupling
        voterMapper.updateFromDto(vDTO, v);
        voterRepository.save(v);
    }
    // ---------------------------------------------------------------------------------------------------s-----------------o
    // To delete an Voter entity in DDBB
    public void deleteVoter(Long voterId) {
        Voter v = voterRepository.findById(voterId)
                .orElseThrow(() -> new EntityNotFoundException("No existe un votante con el ID otorgado"));

        voterRepository.delete(v);
    }
}
