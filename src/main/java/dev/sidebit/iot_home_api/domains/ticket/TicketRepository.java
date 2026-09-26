package dev.sidebit.iot_home_api.domains.ticket;

import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    Optional<TicketEntity> findByNome(String nome);
//    Pode existir ou não algum Ticket cadastrado com o mesmo nome

    @Query("""
                select c
                from TicketEntity c
                where (:id is null or c.id != :id)
                and c.nome = :nome
            """)
    List<TicketEntity> findByNomeAndNotId(@Param("nome") String nome, @Param("id") Integer id);
}
