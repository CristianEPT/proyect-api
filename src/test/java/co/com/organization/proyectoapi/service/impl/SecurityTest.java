package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.LoginDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Funcionario;
import co.com.organization.proyectoapi.persistence.repositories.IFuncionarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SecurityTest {

    private static final Funcionario FUNCIONARIO = new Funcionario();
    private static final Optional<Funcionario> FUNCIONARIO_OPTIONAL = Optional.of(FUNCIONARIO);
    private static final LoginDto LOGIN_DTO = new LoginDto();

    @Mock
    IFuncionarioRepository iFuncionarioRepository;

    @InjectMocks
    SecurityService securityService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        iniciarVariables();
    }

    @Test
    public void loginTest() {
        when(iFuncionarioRepository.validateCredentials("admin", "admin")).thenReturn(FUNCIONARIO_OPTIONAL);
        ResponseDto<LoginDto> response = securityService.login(LOGIN_DTO);
        assertEquals(response.getStatus(), "OK");
        assertEquals(response.getMessage(), "Autenticacion correcta");
        assertEquals(response.getCode(), "0000");
        assertNotNull(response.getData().getToken());
        assertEquals(response.getData().getStatus(), "Ok");
    }

    private void iniciarVariables() {
        FUNCIONARIO.setId(1L);
        FUNCIONARIO.setUser("admin");
        FUNCIONARIO.setPass("admin");

        LOGIN_DTO.setUser("admin");
        LOGIN_DTO.setPass("admin");
    }


}
