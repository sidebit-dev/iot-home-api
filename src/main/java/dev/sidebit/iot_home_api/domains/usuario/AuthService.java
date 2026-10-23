package dev.sidebit.iot_home_api.domains.usuario;

import dev.sidebit.iot_home_api.common.exceptions.UnauthorizedException;
import dev.sidebit.iot_home_api.common.exceptions.ValidationException;
import dev.sidebit.iot_home_api.common.validations.CampoInvalido;
import dev.sidebit.iot_home_api.domains.usuario.dto.AuthResponse;
import dev.sidebit.iot_home_api.domains.usuario.dto.LoginForm;
import dev.sidebit.iot_home_api.domains.usuario.dto.UsuarioCadastroForm;
import dev.sidebit.iot_home_api.domains.usuario.model.UsuarioEntity;
import dev.sidebit.iot_home_api.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;

    public AuthResponse cadastrar(UsuarioCadastroForm form) {

        if (usuarioRepository.existsByLogin(form.login())) {
            throw new ValidationException(List.of(new CampoInvalido("login", "Login já cadastrado...")));
        }

        String senhaCriptografada = encoder.encode(form.senha());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(form.nome());
        usuario.setLogin(form.login());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        return toAuthResponse(usuario);
    }

    public AuthResponse autenticar(LoginForm form) {

        final String mensagem = "Login ou senha inválidos.";

        UsuarioEntity usuario = usuarioRepository.findByLogin(form.login())
                .orElseThrow(() -> new UnauthorizedException(mensagem));

        boolean senhasBatem = encoder.matches(form.senha(), usuario.getSenha());

        if (!senhasBatem) {
            new UnauthorizedException(mensagem);
        }

        return toAuthResponse(usuario);
    }

    private AuthResponse toAuthResponse(UsuarioEntity usuario) {
        var token = jwtService.gerarToken(usuario);
        var expiraEm = jwtService.getExpirationSeconds();
        return new AuthResponse(
                token,
                "Bearer",
                expiraEm,
                usuario.getNome()
        );
    }
}
