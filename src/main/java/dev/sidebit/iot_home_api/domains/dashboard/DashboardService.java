package dev.sidebit.iot_home_api.domains.dashboard;

import dev.sidebit.iot_home_api.domains.dashboard.dto.AtuadosTicketsResumo;
import dev.sidebit.iot_home_api.domains.dashboard.dto.Dashboard;
import dev.sidebit.iot_home_api.domains.dashboard.dto.ResumoStatus;

import dev.sidebit.iot_home_api.domains.sensor.SensorRepository;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private SensorRepository sensorRepository;

    public Dashboard obterDashboardMesAtual(){
        YearMonth mesAtual = YearMonth.now();

        Integer atuados = sensorRepository.somaPorStatusNoPeriodo(StatusSensor.ALARME);
        Integer normais = sensorRepository.somaPorStatusNoPeriodo(StatusSensor.NORMAL);
        Integer total = atuados + normais;

        ResumoStatus resumoStatus = new ResumoStatus(total, atuados, normais);



        List<AtuadosTicketsResumo> atuadosPorTicket = sensorRepository.ticketsAtuados(StatusSensor.ALARME)
                .stream().map(resultado -> new AtuadosTicketsResumo((String) resultado[0]))
                .toList();


        return new Dashboard(
                mesAtual.toString(),
                resumoStatus,
                atuadosPorTicket
                );
    }

}
