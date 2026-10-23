package dev.sidebit.iot_home_api.domains.usuario;

import dev.sidebit.iot_home_api.domains.usuario.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Se existir! Optional
    Optional<UsuarioEntity> findByLogin(String login);

    boolean existsByLogin(String login);
}
