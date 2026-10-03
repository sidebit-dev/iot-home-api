package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<SensorEntity, Integer> {

    Optional<SensorEntity> findByNome(String nome);
//    Pode existir ou não algum Sensor cadastrado com o mesmo nome

    @Query("""
                select c
                from SensorEntity c
                where (:id is null or c.id != :id)
                and c.nome = :nome
            """)
    List<SensorEntity> findByNomeAndNotId(@Param("nome") String nome, @Param("id") Integer id);
}
