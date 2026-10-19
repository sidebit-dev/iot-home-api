package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import dev.sidebit.iot_home_api.domains.event.model.StatusEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

}
