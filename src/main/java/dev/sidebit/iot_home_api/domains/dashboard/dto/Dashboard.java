package dev.sidebit.iot_home_api.domains.dashboard.dto;

import java.util.List;

public record Dashboard(
        String mes,
        ResumoStatus resumo,
        List<AtuadosTicketsResumo> atuadosPorTickets
) {
}
