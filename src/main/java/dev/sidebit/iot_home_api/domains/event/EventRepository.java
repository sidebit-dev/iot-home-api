package dev.sidebit.iot_home_api.domains.event;

import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
