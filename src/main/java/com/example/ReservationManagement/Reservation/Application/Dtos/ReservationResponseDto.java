package com.example.ReservationManagement.Reservation.Application.Dtos;

import com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence.RoomEntity;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.UserEntity;

import java.time.LocalDate;

public class ReservationResponseDto {

    private Integer Id;
    private Long ClientId;
    private Integer RoomId;
    private LocalDate Entry_Date;
    private LocalDate Out_Date;
    private String State;
    private String Services;
    private double Total_Cost;



    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Long getClientId() {
        return ClientId;
    }

    public void setClientId(Long clientId) {
        ClientId = clientId;
    }

    public Integer getRoom_Id() {
        return RoomId;
    }

    public void setRoom_Id(Integer roomId) {
        RoomId = roomId;
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
