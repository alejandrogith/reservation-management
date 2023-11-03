package com.example.ReservationManagement.Room.Application.Ports.Output;


import com.example.ReservationManagement.Room.Domain.RoomDomain;
import com.example.ReservationManagement.Shared.BaseRequestParamsDto;
import com.example.ReservationManagement.Shared.PaginatedDataDto;

public interface IRoomRepository {

    public RoomDomain Save(RoomDomain roomDomain);


    public PaginatedDataDto<RoomDomain> FindAll(BaseRequestParamsDto request);


    public RoomDomain FindById(Integer Id);


    public void Update(RoomDomain roomDomain);


    public void Delete(RoomDomain roomDomain);
}
