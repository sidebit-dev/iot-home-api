package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SensorRepository
        extends JpaRepository<SensorEntity, Integer>,
        JpaSpecificationExecutor<SensorEntity> {

    @Query("""
       select coalesce( count(s.nome), 0)
           from SensorEntity as s
               where s.status = :status
    """)
    Integer somaPorStatusNoPeriodo(
            @Param("status") StatusSensor status
    );

    Optional<SensorEntity> findByNome(String nome);
//    Pode existir ou não algum Sensor cadastrado com o mesmo nome

    @Query("""
                select c
                from SensorEntity as c
                where (:id is null or c.id != :id)
                and c.nome = :nome
            """)
    List<SensorEntity> findByNomeAndNotId(@Param("nome") String nome, @Param("id") Integer id);

    @Query("""
        select s.ticket.nome, count(s.ticket)
        from SensorEntity as s
        where s.status = :status
        group by s.ticket.nome
        order by s.ticket.nome
        """)
    List<Object[]> ticketsAtuados(
            @Param("status") StatusSensor status
    );



}
