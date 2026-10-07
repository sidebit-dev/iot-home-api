package dev.sidebit.iot_home_api.domains.event.model;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sensor_log")

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

    public EventEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusEvent getStatus() {
        return status;
    }

    public void setStatus(StatusEvent status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public SensorEntity getSensor() {
        return sensor;
    }

    public void setSensor(SensorEntity sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", dataEvento=" + dataEvento +
                ", sensor=" + sensor +
                '}';
    }
}
