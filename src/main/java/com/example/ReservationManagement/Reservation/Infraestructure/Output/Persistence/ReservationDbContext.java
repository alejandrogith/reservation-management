package com.example.ReservationManagement.Reservation.Infraestructure.Output.Persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDbContext extends JpaRepository<ReservationEntity,Integer> {}


