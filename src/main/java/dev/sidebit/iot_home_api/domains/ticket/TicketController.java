package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping
    public ResponseEntity<TicketDetalhes> criar(@RequestBody @Valid TicketForm novo){
        TicketDetalhes detalhes = service.criar(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDetalhes> findOne(@PathVariable Integer id){
        var result = service.findOne(id);
        return ResponseEntity.ok(result);
    }

}
