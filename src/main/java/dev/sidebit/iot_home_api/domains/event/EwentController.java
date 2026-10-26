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
public class EwentController {

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
}
