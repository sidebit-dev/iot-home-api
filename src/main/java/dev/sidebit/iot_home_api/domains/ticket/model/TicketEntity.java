package dev.sidebit.iot_home_api.domains.ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ticket")
@Getter
@Setter
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    private String address;

    @Enumerated(EnumType.STRING)
    private StatusTicket status;

    @Column(options = "true, false")
    private Boolean active;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDateTime.now());
    }
}
