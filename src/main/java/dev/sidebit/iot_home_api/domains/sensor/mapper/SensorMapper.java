package dev.sidebit.iot_home_api.domains.sensor.mapper;

import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorEntity toEntity(SensorForm form);

    SensorDetalhes toDetalhes(SensorEntity entity);

    void update(@MappingTarget SensorEntity entity, SensorForm dadosAtualizacao);
}
