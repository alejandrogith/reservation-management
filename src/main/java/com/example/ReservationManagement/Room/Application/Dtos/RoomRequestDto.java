package com.example.ReservationManagement.Room.Application.Dtos;


public class RoomRequestDto{


    private   String RoomNumber;

    private  String Type ;

    private String  Capacity;

    private double PriceNight;

    private boolean Disponibility;

    private String Description;



    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public double getPriceNight() {
        return PriceNight;
    }

    public void setPriceNight(double priceNight) {
        PriceNight = priceNight;
    }

    public boolean isDisponibility() {
        return Disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        Disponibility = disponibility;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

