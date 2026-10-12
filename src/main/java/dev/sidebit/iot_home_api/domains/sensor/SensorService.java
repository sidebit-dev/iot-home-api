package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import dev.sidebit.iot_home_api.domains.sensor.mapper.SensorMapper;
import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;
import dev.sidebit.iot_home_api.domains.ticket.TicketRepository;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.sidebit.iot_home_api.domains.sensor.SensorSpecification.statusEqual;
import static dev.sidebit.iot_home_api.domains.sensor.SensorSpecification.ticketEqual;

@Service
public class SensorService {

    @Autowired
    private SensorValidator validator;
    @Autowired
    private SensorRepository repository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SensorMapper mapper;

    @Transactional
    public SensorDetalhes create(SensorForm form) {

        TicketEntity ticket = ticketRepository.findById(form.ticket_id()).orElseThrow(() -> new RuntimeException("Ticket não encontrado: " + form.ticket_id()));

//        SensorEntity entity = new SensorEntity();
//        entity.setNome(form.nome());
//        entity.setDescricao(form.descricao());
//        entity.setStatus(form.status());
//        entity.setTicket(ticket);

        SensorEntity entity = mapper.toEntity(form);
        entity.setTicket(ticket);

        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public SensorDetalhes findOne(Integer id){
        return repository.findById(id).map(mapper::toDetalhes)
                .orElseThrow(()-> new RegistroNaoEncontradoException());
    }

    @Transactional
    public void update(Integer id, SensorForm dadosAtualizacao) {
        var entity = repository.findById(id).orElseThrow(()-> new RegistroNaoEncontradoException());
        var result = validator.validar(dadosAtualizacao, entity.getId());
        if (result.isInvalido()){
            throw new ValidationException(result.getCamposInvalidos());
        }
        mapper.update(entity, dadosAtualizacao);
//        repository.save(entity); Não precisa devido ao @Transactional
    }

    public Page<SensorDetalhes> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(mapper::toDetalhes);
    }

    @Transactional
    public void ativaDesativa(Integer id){
        var ticket = repository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);

        ticket.setAtivo(!ticket.getAtivo());
//        Opcional devido ao @Transactional
        repository.save(ticket);
    }

    public Page<SensorDetalhes> listar(PageRequest pageRequest, StatusSensor status, Integer ticket_id){

        // select * from SensorEntity where 1 = 1
        Specification<SensorEntity> spec = Specification.unrestricted();

        if(status != null){
            // and status = :status
            spec = spec.and(statusEqual(status));
        }

        if(ticket_id != null){
            spec = spec.and(ticketEqual(ticket_id));
        }

        var resultado = repository.findAll(spec, pageRequest);

        return resultado.map(mapper::toDetalhes);
    }
}
