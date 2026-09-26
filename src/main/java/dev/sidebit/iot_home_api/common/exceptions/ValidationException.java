package dev.sidebit.iot_home_api.common.exceptions;

import dev.sidebit.iot_home_api.common.validations.CampoInvalido;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<CampoInvalido> camposInvalidos;

    public ValidationException(List<CampoInvalido> camposInvalidos){
        super("Erro de validação.");
        this.camposInvalidos = camposInvalidos;
    }

    public List<CampoInvalido> getCamposInvalidos() {
        return camposInvalidos;
    }
}
