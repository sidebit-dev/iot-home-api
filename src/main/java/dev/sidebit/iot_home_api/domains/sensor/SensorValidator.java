package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.common.validations.CampoInvalido;
import dev.sidebit.iot_home_api.common.validations.ValidationResult;
import dev.sidebit.iot_home_api.domains.sensor.dto.SensorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorValidator {

  @Autowired
  private SensorRepository repository;

  public ValidationResult validar(SensorForm form, Integer ticket){
    var result = ValidationResult.novo();

//    Aqui é a nossa validação
//    var isListaNaoVazia = !repository.findByNomeAndNotId(form.nome(), id).isEmpty();
//    if(isListaNaoVazia){
//      result.add(new CampoInvalido("nome","Já cadastrado."));
//    }
//    if(ticket == null){
//      result.add(new CampoInvalido("ticket_id", "Ticket não dncontrado."));
//    if (Boolean.FALSE.equals(ticket.getAtivo())) {
//      result.add(new CampoInvalido("ticket_id", "Ticket Inativo."));
//    }
    return result;
  }
}
