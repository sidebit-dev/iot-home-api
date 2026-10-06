package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.domains.event.dto.EventDetalhes;
import dev.sidebit.iot_home_api.domains.event.dto.EventForm;
import dev.sidebit.iot_home_api.domains.event.mapper.EventMapper;
import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import dev.sidebit.iot_home_api.domains.sensor.SensorRepository;
import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private EventMapper mapper;

    @Transactional
    public EventDetalhes create(EventForm form) {

        SensorEntity sensor = sensorRepository.findById(form.sensor_id()).orElseThrow(() -> new RuntimeException("Sensor não encontrado: " + form.sensor_id()));
        EventEntity entity = new EventEntity();
        entity.setStatus(form.status());
        entity.setDescription(form.description());
        entity.setSensor(sensor);

        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public EventDetalhes findOne(Integer id){
        return repository.findById(id).map(mapper::toDetalhes)
                .orElseThrow(()-> new RegistroNaoEncontradoException());
    }

    @Transactional
    public void update(Integer id, EventForm dadosAtualizacao) {
        var entity = repository.findById(id).orElseThrow(()-> new RegistroNaoEncontradoException());

        mapper.update(entity, dadosAtualizacao);

//        repository.save(entity); Não precisa devido ao @Transactional
    }

    public Page<EventDetalhes> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(mapper::toDetalhes);
    }
}
