package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketDetalhes;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import dev.sidebit.iot_home_api.domains.ticket.mapper.TicketMapper;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketValidator validator;
    @Autowired
    private TicketRepository repository;
    @Autowired
    private TicketMapper mapper;


    public TicketDetalhes criar(TicketForm form) {
        var result = validator.validar(form);

        if (result.isInvalido()){
           throw new ValidationException(result.getCamposInvalidos());
        }
        TicketEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public TicketDetalhes findOne(Integer id){
        return repository.findById(id).map(mapper::toDetalhes)
                .orElseThrow(()-> new RegistroNaoEncontradoException());
    }
}
