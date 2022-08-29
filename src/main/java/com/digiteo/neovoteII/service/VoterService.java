package com.digiteo.neovoteII.service;

import com.digiteo.neovoteII.mapstruct.dtos.VoterDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterGetDTO;
import com.digiteo.neovoteII.mapstruct.dtos.VoterPatchDTO;

import java.util.List;

public interface VoterService {

    List<VoterGetDTO> getVoterGetDTOs ();
    void addNewVoter(VoterDTO vDTO);
    void partialUpdateVoterWithMapper(Long voterId, VoterPatchDTO vDTO);
    void deleteVoter(Long voterId);
}
