package com.example.ReservationManagement.Reservation.Infraestructure.Output.Persistence;

import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationResponseDto;
import com.example.ReservationManagement.Reservation.Application.Ports.Output.IReservationRepository;
import com.example.ReservationManagement.Reservation.Domain.ReservationDomain;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.Exeptions.ResourceNotFoundException;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;


@Repository
public class ReservationRepository implements IReservationRepository {

    @Autowired
    private ModelMapper mapper;

    private ReservationDbContext _dbContext;

    public ReservationRepository(ReservationDbContext dbContext) {
        _dbContext = dbContext;
    }

    @Override
    public ReservationDomain Save(ReservationDomain domain) {

        var Entity=mapper.map(domain,ReservationEntity.class);

        _dbContext.save(Entity);

        return mapper.map(Entity,ReservationDomain.class);
    }

    @Override
    public PaginatedDataDto<ReservationResponseDto> FindAll(BaseRequestParamsDto request) {
        Pageable Pagination=  PageRequest.of(request.getPageIndex()-1,request.getPageSize());

        var Reservations= _dbContext.findAll(Pagination);


        var Results=Reservations.stream()
                .map(x-> {
                    var Reservation=mapper.map(x, ReservationResponseDto.class);
                        Reservation.setRoom_Id( x.getRoom().getId());
                        Reservation.setClientId(x.getUser().getId());

                   return   Reservation;
                })
                .collect(Collectors.toList());

        var ResponsePaginationDto=new PaginatedDataDto<ReservationResponseDto>(Results,request.getPageIndex(),request.getPageSize(),Reservations.getTotalElements());

        return ResponsePaginationDto;
    }

    @Override
    public ReservationDomain FindById(Integer id) {

        var Reservation=_dbContext.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("The Reservation width Id "+id+" does not exist"));

        return mapper.map(Reservation,ReservationDomain.class);
    }

    @Override
    public void Delete(Integer id) {
        var Reservation=_dbContext.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("The Reservation width Id "+id+" does not exist"));

        _dbContext.delete(Reservation);

    }
}
