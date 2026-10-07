package dev.sidebit.iot_home_api.domains.sensor.model;

import dev.sidebit.iot_home_api.domains.event.model.EventEntity;
import dev.sidebit.iot_home_api.domains.ticket.model.TicketEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_sensor")
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

    public SensorEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusSensor getStatus() {
        return status;
    }

    public void setStatus(StatusSensor status) {
        this.status = status;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SensorEntity that = (SensorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SensorEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", ativo=" + ativo +
                ", dataCadastro=" + dataCadastro +
                ", ticket=" + ticket +
                ", events=" + events +
                '}';
    }
}
