package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("sensorS")
public class ListagemSensorController {

    @Autowired
    private SensorService service;

    @GetMapping
    public Page<SensorDetalhes> listar(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "ticket_id", required = false) Integer ticket_id
    ){
        var pageRequest = PageRequest.of(page, size);
        var statusSelecionado = parseStatus(status);

        return service.listar(pageRequest, statusSelecionado, ticket_id);
    }

    private StatusSensor parseStatus(String status) {
        if(status == null || status.isBlank()){
            return null;
        }

        try {
            return StatusSensor.valueOf(status);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_CONTENT, "Parâmetro 'status' inválido, valores aceitos: 'NORMAL' OU 'ALARME'");

        }
    }
}
