package com.example.ReservationManagement.Room.Application.Ports.Input;

import com.example.ReservationManagement.Room.Application.Dtos.RoomRequestDto;
import com.example.ReservationManagement.Room.Application.Dtos.RoomResponseDto;
import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;

public interface IRoomUseCase {
    public RoomResponseDto Save(RoomRequestDto request);

    public PaginatedDataDto<RoomDomain> FindAll(BaseRequestParamsDto request);

    public RoomResponseDto FindById(Integer Id);


    public void Update(int Id, RoomRequestDto request);

    public void Delete(int Id);
}
