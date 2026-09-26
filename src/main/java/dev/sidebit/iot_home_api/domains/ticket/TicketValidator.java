package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.common.validations.CampoInvalido;
import dev.sidebit.iot_home_api.common.validations.ValidationResult;
import dev.sidebit.iot_home_api.domains.ticket.dto.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator {

  @Autowired
  private TicketRepository repository;

  public ValidationResult validar(TicketForm form){
    var result = ValidationResult.novo();

//    Aqui é a nossa validação
    if(repository.findByNome(form.nome()).isPresent()){
      result.add(new CampoInvalido("none","Já cadastrado."));
    }
    return result;
  }
}
