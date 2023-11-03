package com.example.ReservationManagement.Reservation.Application.UseCase;

import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationRequestDto;
import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationResponseDto;
import com.example.ReservationManagement.Reservation.Application.Ports.Input.IReservationUseCase;
import com.example.ReservationManagement.Reservation.Application.Ports.Output.IReservationRepository;
import com.example.ReservationManagement.Reservation.Domain.ReservationDomain;
import com.example.ReservationManagement.Room.Application.Ports.Output.IRoomRepository;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Room.Infraestructure.Adapters.Output.Persistence.RoomEntity;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.Exeptions.ResourceNotFoundException;
import com.example.ReservationManagement.Shared.PaginatedDataDto;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.IUserRepository;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class ReservationUseCase implements IReservationUseCase {

    @Autowired
    private ModelMapper mapper;

    private IRoomRepository _RoomRepository;
    private IUserRepository _userRepository;

    private IReservationRepository _reservationRepository;

    public ReservationUseCase(IRoomRepository roomRepository,IUserRepository userRepository,IReservationRepository reservationRepository) {
        _RoomRepository = roomRepository;
        _userRepository= userRepository;
        _reservationRepository=reservationRepository;
    }

    @Override
    public ReservationResponseDto Save(ReservationRequestDto requestDto) {

        var Room= _RoomRepository.FindById( Integer.parseInt(requestDto.getRoomId()));

        var User= _userRepository.findByemail(requestDto.getEmailUser())
                .orElseThrow(()-> new   ResourceNotFoundException("The User width email "+ requestDto.getEmailUser()+" does not exist"));


        var Days=  requestDto.getEntry_Date().until(requestDto.getOut_Date(), ChronoUnit.DAYS);

        double TotalCost= Room.getPriceNight() * Days;

        var Reservation= new ReservationDomain();
        Reservation.setRoom(  mapper.map(Room, RoomEntity.class)  );
        Reservation.setUser(  mapper.map(User, UserEntity.class)  );
        Reservation.setTotal_Cost(TotalCost);
        Reservation.setOut_Date(requestDto.getOut_Date());
        Reservation.setEntry_Date(requestDto.getEntry_Date());
        Reservation.setState(requestDto.getState());
        Reservation.setServices(requestDto.getServices());

        Reservation=  _reservationRepository.Save(Reservation);

        var ReservationResponse=mapper.map(Reservation,ReservationResponseDto.class);
        ReservationResponse.setRoom_Id(Reservation.getRoom().getId());
        ReservationResponse.setClientId(Reservation.getUser().getId());



        return  ReservationResponse;
    }

    @Override
    public PaginatedDataDto<ReservationResponseDto> FindAll(BaseRequestParamsDto request) {

        return  _reservationRepository.FindAll(request);
    }

    @Override
    public ReservationResponseDto FindById(Integer id) {

        var Reservation=_reservationRepository.FindById(id);

        var ReservationResponse=mapper.map(Reservation,ReservationResponseDto.class);
        ReservationResponse.setRoom_Id(Reservation.getRoom().getId());
        ReservationResponse.setClientId(Reservation.getUser().getId());

        return ReservationResponse;
    }

    @Override
    public void Delete(Integer id) {

        _reservationRepository.Delete(id);
    }


}
