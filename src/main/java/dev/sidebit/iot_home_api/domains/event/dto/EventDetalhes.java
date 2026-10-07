package dev.sidebit.iot_home_api.domains.event.dto;

import dev.sidebit.iot_home_api.domains.event.model.StatusEvent;

import java.util.Date;

public record EventDetalhes(
        Integer id,
        StatusEvent status,
        String description,
        Integer sensor_id,
        Date dataEvento
        ){
}
