package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    Optional<TicketEntity> findByNome(String nome);
//    Pode existir ou não algum Ticket cadastrado com o mesmo nome
}
