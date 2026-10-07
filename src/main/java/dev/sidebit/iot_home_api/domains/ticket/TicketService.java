package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import dev.sidebit.iot_home_api.domains.ticket.mapper.TicketMapper;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    @Autowired
    private TicketValidator validator;
    @Autowired
    private TicketRepository repository;
    @Autowired
    private TicketMapper mapper;


    public TicketDetalhes create(TicketForm form) {
//        var result = validator.validar(form, null);
//
//        if (result.isInvalido()){
//           throw new ValidationException(result.getCamposInvalidos());
//        }
        TicketEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public TicketDetalhes findOne(Integer id){
        return repository.findById(id).map(mapper::toDetalhes)
                .orElseThrow(()-> new RegistroNaoEncontradoException());
    }

    @Transactional
    public void update(Integer id, TicketForm dadosAtualizacao) {
        var entity = repository.findById(id).orElseThrow(()-> new RegistroNaoEncontradoException());
//        var result = validator.validar(dadosAtualizacao, id);
//        if (result.isInvalido()){
//            throw new ValidationException(result.getCamposInvalidos());
//        }
        mapper.update(entity, dadosAtualizacao);

//        repository.save(entity); Não precisa devido ao @Transactional
    }

    public Page<TicketDetalhes> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest).map(mapper::toDetalhes);
    }

    @Transactional
    public void ativarDesativar(Integer id){
        var ticket = repository.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);

        ticket.setAtivo(!ticket.getAtivo());
//        Opcional devido ao @Transactional
        repository.save(ticket);
    }
}
