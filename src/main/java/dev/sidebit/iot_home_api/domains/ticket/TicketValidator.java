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

  public ValidationResult validar(TicketForm form, Integer id){
    var result = ValidationResult.novo();

//    Aqui é a nossa validação
    var isListaNaoVazia = !repository.findByNomeAndNotId(form.nome(), id).isEmpty();
    if(isListaNaoVazia){
      result.add(new CampoInvalido("nome","Já cadastrado."));
    }
    return result;
  }
}
