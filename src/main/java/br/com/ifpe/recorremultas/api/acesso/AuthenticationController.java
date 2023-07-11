package br.com.ifpe.recorremultas.api.acesso;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.recorremultas.modelo.acesso.Usu;
import br.com.ifpe.recorremultas.modelo.acesso.UsuService;
import br.com.ifpe.recorremultas.seguranca.jwt.JwtTokenProvider;
import br.com.ifpe.recorremultas.util.entity.GenericController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController extends GenericController {

    @Autowired
    private UsuService usuService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public Map<Object, Object> signin(@RequestBody AuthenticationRequest data) {

        try {

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));

            Usu usu = this.usuService.findByUsername(data.getUsername());
            String token = jwtTokenProvider.createToken(usu.getUsername(), usu.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(usu.getUsername());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", usu.getUsername());
            model.put("token", token);
            model.put("refresh", refreshToken);

            return model;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}