package com.example.ReservationManagement.Reservation.Infraestructure.Input;

import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationRequestDto;
import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationResponseDto;
import com.example.ReservationManagement.Reservation.Application.Ports.Input.IReservationUseCase;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/reservation")
public class ReservationController {


    private  final IReservationUseCase _reservationUseCase;

    public ReservationController(IReservationUseCase reservationUseCase) {
        _reservationUseCase = reservationUseCase;
    }

    @Operation(summary =  "Create a Reservation")
    @PostMapping()
    public ResponseEntity<ReservationResponseDto> FindAll(@Valid @RequestBody ReservationRequestDto requestParamsDto){


        var Reservation=  _reservationUseCase.Save(requestParamsDto);

        return  ResponseEntity.status(HttpStatus.CREATED).body(Reservation);
    }

    @Operation(summary =  "Find all Reservations")
    @GetMapping()
    public ResponseEntity<PaginatedDataDto<ReservationResponseDto>> FindAll(  BaseRequestParamsDto paramsDto){


        var Reservations=  _reservationUseCase.FindAll(paramsDto);

        return  ResponseEntity.ok(Reservations);
    }

    @Operation(summary =  "Find Reservation By Id")
    @GetMapping("{id}")
    public ResponseEntity<ReservationResponseDto> FindById( @PathVariable Integer id){

        var Reservation=  _reservationUseCase.FindById(id);

        return  ResponseEntity.ok(Reservation);
    }

    @Operation(summary =  "Delete a Reservation By Id")
    @DeleteMapping("{id}")
    public ResponseEntity Delete( @PathVariable Integer id){

       _reservationUseCase.FindById(id);

        return  ResponseEntity.noContent().build();
    }


}
