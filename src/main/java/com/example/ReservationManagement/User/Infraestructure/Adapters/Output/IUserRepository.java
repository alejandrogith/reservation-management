package com.example.ReservationManagement.User.Infraestructure.Adapters.Output;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository  extends JpaRepository<UserEntity, Long> {


    public Optional<UserEntity> findByemail(String Email);



    public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    public Optional<UserEntity> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);


}