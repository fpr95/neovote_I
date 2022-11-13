package com.digiteo.neovoteII.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digiteo.neovoteII.model.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

    @Query(value = "SELECT v FROM voter v WHERE v.v_userName = ?1")
    Optional<Voter> findVoterByUserName(String userName);

    @Query(value = "SELECT v FROM voter v WHERE v.v_rut = ?1")
    Optional<Voter> findVoterByRut(String rut);

    @Query(value = "SELECT v FROM voter v WHERE v.v_phone = ?1")
    Optional<Voter> findVoterByPhone(String phone);

    @Query(value = "SELECT v FROM voter v WHERE v.v_email = ?1")
    Optional<Voter> findVoterByEmail(String email);

}
