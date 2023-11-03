package com.example.ReservationManagement.Reservation.Application.Ports.Output;

import com.example.ReservationManagement.Reservation.Application.Dtos.ReservationResponseDto;
import com.example.ReservationManagement.Reservation.Domain.ReservationDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;

public interface IReservationRepository {

    public ReservationDomain Save(ReservationDomain domain);

    public PaginatedDataDto<ReservationResponseDto> FindAll(BaseRequestParamsDto request);

    public ReservationDomain FindById(Integer id);

    public void Delete(Integer id);
}
