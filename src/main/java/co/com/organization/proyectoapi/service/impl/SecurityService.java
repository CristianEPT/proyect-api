package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.LoginDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Funcionario;
import co.com.organization.proyectoapi.persistence.repositories.IFuncionarioRepository;
import co.com.organization.proyectoapi.service.ISecurityService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityService implements ISecurityService {

    @Autowired
    private IFuncionarioRepository iFuncionarioRepository;

    @Override
    public ResponseDto<LoginDto> login(LoginDto loginDto) {
        String mensaje = "Autenticacion fallida";
        String code = "0001";
        String status = "Error";
        try {
            if (autenticateCredentrials(loginDto.getUser(), loginDto.getPass())) {

                String secretKey = "ProyectApiSecret";
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ADMINISTRATOR");
                String token = Jwts
                        .builder()
                        .setId("ProyectApiJWT")
                        .setSubject(loginDto.getUser())
                        .claim("authorities",
                                grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();
                loginDto.setToken("Bearer " + token);
                loginDto.setStatus("Ok");

                mensaje = "Autenticacion correcta";
                code = "0000";
                status = "OK";
            }
        } catch (Exception e) {
            loginDto.setStatus("Error");
            mensaje = e.getMessage();
        }
        return ResponseDto.<LoginDto>builder()
                .code(code)
                .status(status)
                .message(mensaje)
                .data(loginDto)
                .build();
    }

    private boolean autenticateCredentrials(String user, String pass) {
        Optional<Funcionario> funcionario = iFuncionarioRepository.validateCredentials(user, pass);
        return funcionario.isPresent();
    }

}
