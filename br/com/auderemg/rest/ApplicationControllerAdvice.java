package br.com.auderemg.rest;

import br.com.auderemg.rest.ecxeption.APIErros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErros handleValidationErrors(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new APIErros(messages);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusExeption(ResponseStatusException ex){
        String mensagemErro = ex.getMessage();
        HttpStatus codigoStatus = ex.getStatus();
        APIErros apiErros = new APIErros(mensagemErro);
        return new ResponseEntity(apiErros, codigoStatus);
    }

}
