package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Persona;
import co.com.organization.proyectoapi.persistence.entities.Tarjeta;
import co.com.organization.proyectoapi.persistence.repositories.ITarjetaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class TarjetaTest {

    private static final Tarjeta TARJETA = new Tarjeta();
    private static final Optional<Tarjeta> TARJETA_OPTIONAL = Optional.of(TARJETA);
    private static final TarjetaDto TARJETA_DTO = new TarjetaDto();
    private static final Persona PERSONA = new Persona();


    @Mock
    ITarjetaRepository iTarjetaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    TarjetaService tarjetaService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapper = new ModelMapper();
        iniciarVariables();
    }

    @Test
    public void updateTarjetaTest() {
        when(iTarjetaRepository.findTarjetaById(1L)).thenReturn(TARJETA_OPTIONAL);
        ResponseDto<TarjetaDto> response = tarjetaService.updateTarjeta(1L, TARJETA_DTO);
        assertEquals(response.getCode(), "0000");
        assertEquals(response.getStatus(), "OK");
        assertEquals(response.getMessage(), "Tarjeta actualizada correctamente");
        assertNotNull(response.getData());
    }

    private void iniciarVariables() {
        PERSONA.setId(1L);
        PERSONA.setTipoDoc("CC");
        PERSONA.setNumDoc("159");
        PERSONA.setNombre("Cristian");
        PERSONA.setActivo(true);

        TARJETA.setId(1L);
        TARJETA.setActivo(true);
        TARJETA.setCapacidad(1000L);
        TARJETA.setPersona(PERSONA);
        TARJETA.setNumTarjeta("456852");

        TARJETA_DTO.setCapacidad(1000L);
        TARJETA_DTO.setActivo(false);

    }

}
