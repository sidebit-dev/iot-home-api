package dev.sidebit.iot_home_api.domains.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroForm(
        @NotBlank(message = "Informe o nome do usuário")
        String nome,
        @NotBlank(message = "Informe o login")
        String login,
        @NotBlank(message = "Informe a senha")
        @Size(min = 4, message = "A senha deve ter pelo menos 4 caracteres")
        String senha
) {
}
