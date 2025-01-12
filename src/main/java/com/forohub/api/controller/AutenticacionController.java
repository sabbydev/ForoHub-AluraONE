package com.forohub.api.controller;

import com.forohub.api.dominio.usuario.AutenticacionUsuarioDTO;
import com.forohub.api.dominio.usuario.Usuario;
import com.forohub.api.infra.seguridad.JWTDTO;
import com.forohub.api.infra.seguridad.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTDTO> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var stringJWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTDTO(stringJWT));
    }

}
