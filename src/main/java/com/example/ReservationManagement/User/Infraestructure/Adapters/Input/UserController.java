package com.example.ReservationManagement.User.Infraestructure.Adapters.Input;



import com.example.ReservationManagement.User.Application.Dtos.LoginRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.LoginResponseDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterRequestDto;
import com.example.ReservationManagement.User.Application.Dtos.RegisterResponseDto;
import com.example.ReservationManagement.User.Application.Ports.Input.IUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    private final IUserUseCase _userUseCase;

    public UserController(IUserUseCase userUseCase) {
        this._userUseCase = userUseCase;
    }




    @Operation(summary =  "Create a new User")
    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> Register(@Valid @RequestBody RegisterRequestDto requestDto){

       var Response= _userUseCase.Register(requestDto);

        return  ResponseEntity.status(HttpStatus.CREATED).body(Response);
    }

    @PermitAll
    @Operation(summary =  "Login a User")
    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> Login(@Valid @RequestBody LoginRequestDto requestDto){

        var Response= _userUseCase.Login(requestDto);

        return  ResponseEntity.status(HttpStatus.OK).body(Response);
    }
}
