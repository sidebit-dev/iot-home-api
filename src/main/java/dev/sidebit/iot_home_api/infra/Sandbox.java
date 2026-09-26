package dev.sidebit.iot_home_api.infra;

import dev.sidebit.iot_home_api.domains.ticket.TicketRepository;
import dev.sidebit.iot_home_api.domains.ticket.model.StatusTicket;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sandbox implements CommandLineRunner {

    @Autowired
    private TicketRepository repository;

    public void salvarTicket(){
        TicketEntity ticket = new TicketEntity();

        ticket.setNome("Sistema de Proteção");
        ticket.setDescricao("Instalação residencial de 4 sensores de Infra-vermelhos");
        ticket.setAddress("Rua Ladeira Geral s/n - Sapopemba - SP");
//        ticket.setActive(true);
        ticket.setStatus(StatusTicket.ANDAMENTO);

        repository.save(ticket);
    }

    @Override
    public void run(String... args) throws Exception {
//        salvarTicket();
    }
}
