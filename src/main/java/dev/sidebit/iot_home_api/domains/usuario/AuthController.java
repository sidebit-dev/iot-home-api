package dev.sidebit.iot_home_api.domains.usuario;

import dev.sidebit.iot_home_api.domains.usuario.dto.AuthResponse;
import dev.sidebit.iot_home_api.domains.usuario.dto.LoginForm;
import dev.sidebit.iot_home_api.domains.usuario.dto.UsuarioCadastroForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public AuthResponse cadastrar(@RequestBody @Valid UsuarioCadastroForm form){
        return service.cadastrar(form);
    }

    @PostMapping("/signin")
    public AuthResponse autenticar(@RequestBody @Valid LoginForm form){
        return service.autenticar(form);
    }
}
