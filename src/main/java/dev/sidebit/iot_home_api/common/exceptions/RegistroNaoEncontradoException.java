package dev.sidebit.iot_home_api.common.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException() {
        super("Registro não encontrado.");
    }
}
