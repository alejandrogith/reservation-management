package com.example.ReservationManagement.Reservation.Application.Ports.Input;

import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationRequestDto;
import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationResponseDto;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;

public interface IReservationUseCase {

    public ReservationResponseDto Save(ReservationRequestDto requestDto);

    public PaginatedDataDto<ReservationResponseDto> FindAll(BaseRequestParamsDto request);


    public ReservationResponseDto FindById(Integer id);

    public void Delete(Integer id);
}
