package com.example.ReservationManagement.Reservation.Application.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ReservationRequestDto {


    @Email(message = "Must be a correctly formatted email address")
    @NotBlank(message = "Email is mandatory")
    private String EmailUser;
    @NotBlank(message = "RoomId is mandatory")
    private String RoomId;


    private LocalDate Entry_Date;

    private LocalDate Out_Date;
    @NotBlank(message = "State is mandatory")
    private String State;
    @NotBlank(message = "Services is mandatory")
    private String Services;

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
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


}
