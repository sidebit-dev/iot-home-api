package dev.sidebit.iot_home_api.domains.event.model;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_log")
@Getter
@Setter
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StatusEvent status;

    @Column(nullable = false)
    private String description;

    @Column(name = "dt_evento")
    private LocalDateTime dataEvento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sensor_id", nullable = false)
    private SensorEntity sensor;

    @PrePersist
    public void prePersist(){
        setDataEvento(LocalDateTime.now());
    }
}
