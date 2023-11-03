package com.example.ReservationManagement.Reservation.Infraestructure.Output.Persistence;


import com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence.RoomEntity;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate Entry_Date;
    private LocalDate Out_Date;
    private String State;
    private String Services;
    private double Total_Cost;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;



    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer _id) {
        _id = id;
    }



    public LocalDate getEntry_Date() {
        return Entry_Date;
    }

    public void setEntry_Date(LocalDate entry_Date) {
        Entry_Date = entry_Date;
    }

    public LocalDate getOut_Date() {
        return Out_Date;
    }

    public void setOut_Date(LocalDate out_Date) {
        Out_Date = out_Date;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String services) {
        Services = services;
    }

    public double getTotal_Cost() {
        return Total_Cost;
    }

    public void setTotal_Cost(double total_Cost) {
        Total_Cost = total_Cost;
    }


}

