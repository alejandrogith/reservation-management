package com.example.ReservationManagement.User.Application.UseCase;

import com.example.ReservationManagement.Shared.Exeptions.ResourceNotFoundException;
import com.example.ReservationManagement.User.Application.Dtos.LoginRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.LoginResponseDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterResponseDto;
import com.example.ReservationManagement.User.Application.Ports.Input.IUserUseCase;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.IRoleRepository;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.IUserRepository;
import com.example.ReservationManagement.User.Infraestructure.Adapters.Output.UserEntity;
import com.example.ReservationManagement.User.Infraestructure.SecurityConfigurations.JwtProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserUseCase implements IUserUseCase {


    @Autowired
    private ModelMapper mapper;

    private final JwtProvider _jwtProvider;
    private final AuthenticationManager _authenticationManager;

    private final IUserRepository _userRepository;
    private final IRoleRepository _roleRepository;

    private final  PasswordEncoder _passwordEncoder;

    public UserUseCase(JwtProvider jwtProvider, AuthenticationManager authenticationManager, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this._jwtProvider = jwtProvider;
        this._authenticationManager = authenticationManager;
        this._userRepository = userRepository;
        this._roleRepository = roleRepository;
        this._passwordEncoder = passwordEncoder;
    }


    @Override
    public RegisterResponseDto Register(RegisterRequestDto requestDto) {

        requestDto.setPassword(_passwordEncoder.encode(requestDto.getPassword()));
        var User = mapper.map(requestDto, UserEntity.class);


        _userRepository.save(User);

        var TOKEN = _jwtProvider.generateAccesToken(User.getUsername());

        var Response = new RegisterResponseDto();
        Response.setEmail(User.getEmail());
        Response.setUsername(User.getUsername());
        Response.setToken(TOKEN);


        return Response;
    }

    @Override
    public LoginResponseDto Login(LoginRequestDto requestDto) {



            Authentication authenticate = _authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(), requestDto.getPassword()));



        var User= _userRepository.findByemail(requestDto.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("The User width email "+ requestDto.getEmail()+" does not exist"));

        var TOKEN= _jwtProvider.generateAccesToken(User.getUsername());

        var Response = new LoginResponseDto();
        Response.setEmail(requestDto.getEmail());
        Response.setUsername(User.getUsername());
        Response.setToken(TOKEN);



        return Response;
    }


}
