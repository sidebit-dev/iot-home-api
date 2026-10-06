package dev.sidebit.iot_home_api.domains.sensor.dto;

import dev.sidebit.iot_home_api.domains.event.dto.EventDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;

import java.util.List;

public record SensorDetalhes(
        Integer id,
        String nome,
        String descricao,
        StatusSensor status,
        Boolean ativo,
        Integer ticket_id,
        List<EventDetalhes> events
        ) {
}
