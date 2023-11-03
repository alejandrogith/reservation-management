package com.example.ReservationManagement.Shared.Exeptions;




public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String Message) {
        super(Message);

    }


}