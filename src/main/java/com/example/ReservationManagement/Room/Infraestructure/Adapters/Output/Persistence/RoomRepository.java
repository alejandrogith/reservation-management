package com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence;


import com.example.ReservationManagement.Room.Application.Ports.Output.IRoomRepository;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.Exeptions.ResourceNotFoundException;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;


@Repository
public class RoomRepository implements IRoomRepository {

    @Autowired
    private ModelMapper mapper;

    private  final RoomDbContext _repository;

    public  RoomRepository(RoomDbContext repository){
        this._repository = repository;
    }

    @Override
    public RoomDomain Save(RoomDomain roomDomain) {
        RoomEntity roomEntity= mapper.map(roomDomain,RoomEntity.class);
        _repository.save(roomEntity);


        return mapper.map(roomEntity,RoomDomain.class);
    }

    @Override
    public PaginatedDataDto<RoomDomain> FindAll(BaseRequestParamsDto request) {


        Pageable Pagination=  PageRequest.of(request.getPageIndex()-1,request.getPageSize());

        var Rooms= _repository.findAll(Pagination);


        var Results=Rooms.stream()
                .map(x-> mapper.map(x,RoomDomain.class))
                .collect(Collectors.toList());

        var ResponsePaginationDto=new PaginatedDataDto<RoomDomain>(Results,request.getPageIndex(),request.getPageSize(),Rooms.getTotalElements());

        return ResponsePaginationDto;
    }

    @Override
    public RoomDomain FindById(Integer Id) {

        var Entity= _repository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("The Room with the id: "+ Id.toString() +" does not exist"));;

        return mapper.map(Entity,RoomDomain.class) ;
    }

    @Override
    public void Update(RoomDomain roomDomain) {

        var Entity= mapper.map(roomDomain,RoomEntity.class);
        _repository.save(Entity);
    }

    @Override
    public void Delete(RoomDomain roomDomain) {
        var Entity= mapper.map(roomDomain,RoomEntity.class);
        _repository.delete(Entity);
    }


}









