package dev.sidebit.iot_home_api.domains.ticket.mapper;

import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketEntity toEntity(TicketForm form);

    TicketDetalhes toDetalhes(TicketEntity entity);
}
