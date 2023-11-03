package com.example.ReservationManagement.Shared.HandleExeptions;


import com.example.ReservationManagement.Shared.Exeptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private record ErrorResponse(String Type, int Status, String Message){}


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        var errorDetails = new ErrorResponse(
                "https://tools.ietf.org/html/rfc7231#section-6.5.4",
                404,
                exception.getMessage()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(BadCredentialsException exception) {

        var errorDetails = new ErrorResponse(
                "",
                401,
                "Bad credentials"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGlobalException(Exception exception,WebRequest webRequest){

        var res= exception.getStackTrace();

        var errorDetails = new ErrorResponse(
                "https://datatracker.ietf.org/doc/html/rfc7231#section-6.6.1",
                500,
                exception.getMessage()
        );

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
