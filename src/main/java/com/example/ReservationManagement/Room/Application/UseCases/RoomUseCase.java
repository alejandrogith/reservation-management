package com.example.ReservationManagement.Room.Application.UseCases;


import com.example.ReservationManagement.Room.Application.Dtos.RoomRequestDto;
import com.example.ReservationManagement.Room.Application.Dtos.RoomResponseDto;
import com.example.ReservationManagement.Room.Application.Ports.Input.IRoomUseCase;
import com.example.ReservationManagement.Room.Application.Ports.Output.IRoomRepository;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomUseCase implements IRoomUseCase {


    @Autowired
    private ModelMapper mapper;

    private  final IRoomRepository _roomRepo;

    public  RoomUseCase(IRoomRepository roomRepo){
        _roomRepo=roomRepo;
    }

    @Override
    public RoomResponseDto Save(RoomRequestDto request) {

        RoomDomain Room= mapper.map(request,RoomDomain.class);

        Room=  _roomRepo.Save(Room);

        return mapper.map(Room,RoomResponseDto.class);
    }

    @Override
    public PaginatedDataDto<RoomDomain> FindAll(BaseRequestParamsDto request) {

        return _roomRepo.FindAll(request);
    }

    @Override
    public RoomResponseDto FindById(Integer Id) {


        var Room=_roomRepo.FindById(Id);

        return mapper.map(Room,RoomResponseDto.class);
    }

    @Override
    public void Update(int Id, RoomRequestDto request) {

        var Room=_roomRepo.FindById(Id);

        Room=mapper.map(request,RoomDomain.class);
        Room.setId(Id);

        _roomRepo.Update(Room);
    }

    @Override
    public void Delete(int Id) {

        var Room=_roomRepo.FindById(Id);

        _roomRepo.Delete(Room);

    }
}
