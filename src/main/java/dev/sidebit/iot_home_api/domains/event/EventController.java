package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.domains.event.dto.EventDetalhes;
import dev.sidebit.iot_home_api.domains.event.dto.EventForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;


@RestController
@RequestMapping("ewent")
//@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventNotificationService notificationService;

    @Autowired
    private EventService service;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamEvents() {
        return notificationService.addEmitter();
    }

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
        var pageRequest = PageRequest.of(page, size, Sort.by("dataEvento").descending());
        return service.findAll(pageRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarTodos(@RequestBody List<Integer> ids){
        service.deletarTodos(ids);
        return ResponseEntity.noContent().build();
    }
}
