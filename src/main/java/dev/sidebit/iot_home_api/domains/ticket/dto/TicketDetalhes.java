package dev.sidebit.iot_home_api.domains.ticket.dto;

import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.model.StatusTicket;

import java.util.List;

public record TicketDetalhes(
        Integer id,
        String nome,
        String descricao,
        String endereco,
        StatusTicket status,
        Boolean ativo,
        List<SensorDetalhes> sensors
        ) {
}
