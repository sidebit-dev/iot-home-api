package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("tickets")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping
    public ResponseEntity<TicketDetalhes> create(@RequestBody @Valid TicketForm novo){
        TicketDetalhes detalhes = service.create(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDetalhes> findOne(@PathVariable Integer id){
        var result = service.findOne(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody TicketForm dadosAtual){
        service.update(id, dadosAtual);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<TicketDetalhes> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.findAll(pageRequest);
    }

    @PatchMapping("{id}/ativo")
    public ResponseEntity<Void> ativaDerivativa(@PathVariable Integer id){
        service.ativarDesativar(id);
        return ResponseEntity.noContent().build();
    }
}
