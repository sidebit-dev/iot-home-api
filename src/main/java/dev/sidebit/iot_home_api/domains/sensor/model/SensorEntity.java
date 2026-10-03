package dev.sidebit.iot_home_api.domains.sensor.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDateTime.now());
        setAtivo(true);
    }
}
