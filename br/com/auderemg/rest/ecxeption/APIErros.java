package br.com.auderemg.rest.ecxeption;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class APIErros {
    @Getter
    public List<String> errors;

    public APIErros(List<String> errors){
        this.errors = errors;
    }

    public APIErros(String message){
        this.errors = Arrays.asList(message);
    }
}
