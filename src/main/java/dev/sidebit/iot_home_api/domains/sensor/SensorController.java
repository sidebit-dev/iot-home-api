package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import dev.sidebit.iot_home_api.domains.ticket.TicketService;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sensors")
//@CrossOrigin("*")
//CORS - Cross Origin Resource Sharing
public class SensorController {

    @Autowired
    private SensorService service;
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<SensorDetalhes> create(@RequestBody @Valid SensorForm novo){
//        System.out.println(novo);
        SensorDetalhes detalhes = service.create(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

//    OUTRA FORMA DE FAZER
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public SensorDetalhes create(@RequestBody @Valid SensorForm novo){
//        return service.create(novo);
//    }

    @GetMapping("tickets-disponiveis")
    public List<TicketDetalhes> listTicketsDisponiveis(){
        return ticketService.listarAtivos();
    }

    @GetMapping("{id}")
    public ResponseEntity<SensorDetalhes> findOne(@PathVariable Integer id){
        var result = service.findOne(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody SensorForm dadosAtual){
        service.update(id, dadosAtual);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<SensorDetalhes> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.findAll(pageRequest);
    }

    @PatchMapping("{id}/ativo")
    public ResponseEntity<Void> ativaDesavativa(@PathVariable Integer id){
        service.ativaDesativa(id);
        return ResponseEntity.noContent().build();
    }
}
