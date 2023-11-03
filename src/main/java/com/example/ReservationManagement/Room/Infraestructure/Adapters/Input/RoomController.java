package com.example.ReservationManagement.Room.Infraestructure.Adapters.Input;

import com.example.ReservationManagement.Room.Application.Dtos.RoomRequestDto;
import com.example.ReservationManagement.Room.Application.Dtos.RoomResponseDto;
import com.example.ReservationManagement.Room.Application.Ports.Input.IRoomUseCase;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/room")
public class RoomController {

    @Autowired
    private ModelMapper mapper;

    private final IRoomUseCase _roomUseCase;

    public RoomController(IRoomUseCase  roomUseCase) {
        this._roomUseCase = roomUseCase;
    }

    @Operation(summary =  "Create a new Room")
    @PostMapping
    public ResponseEntity<RoomResponseDto> Save(@RequestBody RoomRequestDto requestDto){

        var response= _roomUseCase.Save(requestDto);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary =  "Find All Rooms")
    @GetMapping()
    public ResponseEntity<PaginatedDataDto<RoomDomain>> FindAll(BaseRequestParamsDto requestParamsDto){


        var response=_roomUseCase.FindAll(requestParamsDto);

        return  ResponseEntity.ok(response);
    }


    @Operation(summary =  "Find Rooms By Id")
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> FindById(@PathVariable Integer id){


        var response=_roomUseCase.FindById(id);

        return  ResponseEntity.ok(response);
    }

    @Operation(summary =  "Update a Room ")
    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable int id, @RequestBody RoomRequestDto requestDto){

        _roomUseCase.Update(id,requestDto);


        return  ResponseEntity.noContent().build();
    }

    @Operation(summary =  "Delete a Room ")
    @DeleteMapping("/{id}")
    public ResponseEntity Update(@PathVariable int id){

        _roomUseCase.Delete(id);


        return  ResponseEntity.noContent().build();
    }

}


