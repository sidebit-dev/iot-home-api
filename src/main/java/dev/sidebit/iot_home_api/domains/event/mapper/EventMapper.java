package dev.sidebit.iot_home_api.domains.event.mapper;

import dev.sidebit.iot_home_api.domains.event.dto.EventDetalhes;
import dev.sidebit.iot_home_api.domains.event.dto.EventForm;
import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventEntity toEntity(EventForm form);

    EventDetalhes toDetalhes(EventEntity entity);

    void update(@MappingTarget EventEntity entity, EventForm dadosAtualizacao);
}
