package com.digiteo.neovoteII.service;

import com.digiteo.neovoteII.mapstruct.dtos.VoterDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;

import java.util.List;

public interface VoterService {

    List<VoterGetDTO> getVoterGetDTOs ();
    void addNewVoter(VoterDTO vDTO);
    void partialUpdateVoterWithMapper(Long voterId,VoterDTO vDTO);
    void deleteVoter(Long voterId);
}
