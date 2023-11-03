package com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDbContext extends JpaRepository<RoomEntity,Integer> {}
