package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.domains.event.dto.EventDetalhes;
import dev.sidebit.iot_home_api.domains.event.dto.EventForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("ewent")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<EventDetalhes> create(@RequestBody @Valid EventForm novo){
        EventDetalhes detalhes = service.create(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDetalhes> findOne(@PathVariable Integer id){
        var result = service.findOne(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody EventForm dadosAtual){
        service.update(id, dadosAtual);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<EventDetalhes> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        var pageRequest = PageRequest.of(page, size);
        return service.findAll(pageRequest);
    }
}
