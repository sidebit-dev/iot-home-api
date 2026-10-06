package dev.sidebit.iot_home_api.domains.sensor.dto;

import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SensorForm(
    @NotBlank(message = "Campo obrigatório.")
    String nome,
    @NotNull(message = "Campo obrigatório.")
    String descricao,
    StatusSensor status,
    Integer ticket_id
) {
}
