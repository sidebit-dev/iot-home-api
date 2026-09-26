package dev.sidebit.iot_home_api.domains.ticket.dto;

import dev.sidebit.iot_home_api.domains.ticket.model.StatusTicket;

public record TicketForm(
    String nome,
    String descricao,
    String endereco,
    StatusTicket status) {

}
