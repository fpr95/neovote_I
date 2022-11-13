package com.digiteo.neovoteII.repository;

import com.digiteo.neovoteII.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "SELECT a FROM admin a WHERE a.a_userName = ?1")
    Optional<Admin> findAdminByUserName(String userName);

    @Query(value = "SELECT a FROM admin a WHERE a.a_rut = ?1")
    Optional<Admin> findAdminByRut(String rut);

    @Query(value = "SELECT a FROM admin a WHERE a.a_phone = ?1")
    Optional<Admin> findAdminByPhone(String phone);

    @Query(value = "SELECT a FROM admin a WHERE a.a_email = ?1")
    Optional<Admin> findAdminByEmail(String email);
}
