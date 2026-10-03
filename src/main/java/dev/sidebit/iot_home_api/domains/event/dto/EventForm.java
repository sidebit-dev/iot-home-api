package dev.sidebit.iot_home_api.domains.event.dto;

import dev.sidebit.iot_home_api.domains.event.model.StatusEvent;
import jakarta.validation.constraints.NotNull;

public record EventForm(

    StatusEvent status,
    String description){
}
