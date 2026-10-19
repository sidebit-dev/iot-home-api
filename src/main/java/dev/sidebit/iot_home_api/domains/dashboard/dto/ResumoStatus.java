package dev.sidebit.iot_home_api.domains.dashboard.dto;

public record ResumoStatus(
        Integer sensoresTotais,
        Integer sensoresAtuados,
        Integer sensoresNormais
) {

}
