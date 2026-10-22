package dev.sidebit.iot_home_api.common.exceptions;

public class UnauthrizedException extends RuntimeException{
    public UnauthrizedException(String message) {
        super(message);
    }
}
