package com.digiteo.neovoteII.repository;

import com.digiteo.neovoteII.model.Admin;
import com.digiteo.neovoteII.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "SELECT a FROM admin a WHERE a.a_phone = ?1")
    Optional<Voter> findAdminByPhone(String phone);

    @Query(value = "SELECT a FROM admin a WHERE a.a_email = ?1")
    Optional<Voter> findAdminByEmail(String email);
}
