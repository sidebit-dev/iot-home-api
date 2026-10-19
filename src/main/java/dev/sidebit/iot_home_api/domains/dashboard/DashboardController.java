package dev.sidebit.iot_home_api.domains.dashboard;

import dev.sidebit.iot_home_api.domains.dashboard.dto.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public Dashboard obterDashboardMesAtual(){
        return service.obterDashboardMesAtual();
    }
}
