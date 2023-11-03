package com.example.ReservationManagement.User.Application.Ports.Input;

import com.example.ReservationManagement.User.Application.Dtos.LoginRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.LoginResponseDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterResponseDto;

public interface IUserUseCase {
    public RegisterResponseDto Register(RegisterRequestDto requestDto);

    public LoginResponseDto Login(LoginRequestDto requestDto);
}
