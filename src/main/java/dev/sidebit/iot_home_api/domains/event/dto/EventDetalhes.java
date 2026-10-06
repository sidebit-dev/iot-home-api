package dev.sidebit.iot_home_api.domains.event.dto;

import dev.sidebit.iot_home_api.domains.event.model.StatusEvent;

public record EventDetalhes(
        Integer id,
        StatusEvent status,
        String description,
        Integer sensor_id
        ){
}
