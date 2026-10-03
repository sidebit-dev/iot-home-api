package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.common.exceptions.RegistroNaoEncontradoException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorDetalhes;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import dev.sidebit.iot_home_api.domains.sensor.mapper.SensorMapper;
import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
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
    private SensorMapper mapper;


    public SensorDetalhes create(SensorForm form) {
        var result = validator.validar(form, null);

        if (result.isInvalido()){
           throw new ValidationException(result.getCamposInvalidos());
        }
        SensorEntity entity = mapper.toEntity(form);
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
