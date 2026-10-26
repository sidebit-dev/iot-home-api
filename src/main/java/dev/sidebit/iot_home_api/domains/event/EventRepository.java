package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.domains.event.model.EventEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM EventEntity e where e.id in :ids")
    void  deletarPaginaEventos(@Param("ids")List<Integer> ids);


    @Transactional
    @Modifying
    @Query("DELETE FROM EventEntity")
    void deletarTodos();
}
