package dev.sidebit.iot_home_api.domains.ticket.model;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_ticket")
@Getter
@Setter
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column
    private String endereco;

    @Enumerated(EnumType.STRING)
    private StatusTicket status;

    @Column
    private Boolean ativo;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SensorEntity> sensors = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDateTime.now());
        setAtivo(true);
    }
}
