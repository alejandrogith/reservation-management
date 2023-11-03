package com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence;


import com.example.ReservationManagement.Reservation.Infraestructure.Output.Persistence.ReservationEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Room")
public class RoomEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private  String RoomNumber;

    private  String Type ;

    private String  Capacity;

    private double PriceNight;

    private boolean Disponibility;

    private String Description;



    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
