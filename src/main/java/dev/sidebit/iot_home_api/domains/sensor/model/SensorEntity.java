package dev.sidebit.iot_home_api.domains.sensor.model;

import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_sensor")
@Getter
@Setter
public class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusSensor status;

    @Column
    private Boolean ativo;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EventEntity> events = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDateTime.now());
        setAtivo(true);
    }
}
