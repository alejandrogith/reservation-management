package com.example.ReservationManagement.User.Infraestructure.Adapters.Output;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository  extends JpaRepository<RoleEntity, Long> {

    public Optional<RoleEntity> findByName(String name);


}