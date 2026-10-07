package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import dev.sidebit.iot_home_api.domains.sensor.mapper.SensorMapper;
import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import dev.sidebit.iot_home_api.domains.ticket.TicketRepository;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var result = validator.validar(dadosAtualizacao, id);
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
}
