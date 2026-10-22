package dev.sidebit.iot_home_api.domains.usuario.dto;

public record AuthResponse(
        String token,
        String tipo,
        long expiraEm,
        String nome

) {
}
