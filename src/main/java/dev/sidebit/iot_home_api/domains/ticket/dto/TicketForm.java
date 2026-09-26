package dev.sidebit.iot_home_api.domains.ticket.dto;

import dev.sidebit.iot_home_api.domains.ticket.model.StatusTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketForm(
    @NotBlank(message = "Campo obrigatório.")
    String nome,
    @NotNull(message = "Campo obrigatório.")
    String descricao,
    String endereco,
    StatusTicket status) {
}
