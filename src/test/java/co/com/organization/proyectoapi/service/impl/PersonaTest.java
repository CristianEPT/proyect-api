package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.PersonaDto;
import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Persona;
import co.com.organization.proyectoapi.persistence.entities.Tarjeta;
import co.com.organization.proyectoapi.persistence.repositories.IPersonaRepository;
import co.com.organization.proyectoapi.persistence.repositories.ITarjetaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PersonaTest {

    private static final PersonaDto PERSONA_DTO = new PersonaDto();
    private static final Persona PERSONA = new Persona();
    private static final List<Persona> PERSONAS = new ArrayList<>();
    private static final Tarjeta TARJETA = new Tarjeta();
    private static final List<Tarjeta> TARJETAS = new ArrayList<>();
    private static final TarjetaDto TARJETA_DTO = new TarjetaDto();

    @Mock
    IPersonaRepository iPersonaRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ITarjetaRepository iTarjetaRepository;

    @InjectMocks
    PersonaService personaService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapper = new ModelMapper();
        iniciarVariables();
    }

    @Test
    public void createPersonaTest() {
        ResponseDto<PersonaDto> response = personaService.createPersona(PERSONA_DTO);
        assertEquals(response.getCode(), "0000");
        assertEquals(response.getStatus(), "OK");
        assertEquals(response.getMessage(), "Creado correctamente");
        assertNotNull(response.getData());
    }

    @Test
    public void findAllPersonasTest() {
        when(iPersonaRepository.findAll()).thenReturn(PERSONAS);
        ResponseDto<List<PersonaDto>> response = personaService.findAllPersonas();
        assertEquals("Registros no encontrados", response.getMessage());
        assertEquals("OK", response.getStatus());
        assertEquals("0000", response.getCode());
        assertNotNull(response.getData());
    }

    @Test
    public void updatePersonaTest() {
        when(iPersonaRepository.findPersonaById(1L)).thenReturn(Optional.of(PERSONA));
        ResponseDto<PersonaDto> response = personaService.updatePersona(1L, PERSONA_DTO);
        assertNotNull(response.getData());
        assertEquals("OK", response.getStatus());
        assertEquals("0000", response.getCode());
        assertEquals("Registro actualizados correctamente", response.getMessage());
    }

    @Test
    public void findAllTarjetasTest() {
        when(iPersonaRepository.findPersonaById(1L)).thenReturn(Optional.of(PERSONA));
        ResponseDto<List<TarjetaDto>> response = personaService.findAllTarjetas(1L);
        assertEquals("Registros entcontrados", response.getMessage());
        assertEquals("OK", response.getStatus());
        assertEquals("0000", response.getCode());
        assertNotNull(response.getData());
    }

    @Test
    public void createTarjeta() {
        when(iPersonaRepository.findPersonaById(1L)).thenReturn(Optional.of(PERSONA));
        ResponseDto<TarjetaDto> response = personaService.createTarjeta(1L, TARJETA_DTO);
        assertEquals("Tarjeta creada correctamente", response.getMessage());
        assertEquals("OK", response.getStatus());
        assertEquals("0000", response.getCode());
        assertNotNull(response.getData());
    }

    public void iniciarVariables() {

        PERSONA_DTO.setTipoDoc("CC");
        PERSONA_DTO.setNumDoc("159456");
        PERSONA_DTO.setNombre("Cristian");
        PERSONA_DTO.setActivo(true);

        TARJETA.setNumTarjeta("156787");
        TARJETA.setCapacidad(100000L);
        TARJETA.setId(1L);
        TARJETA.setActivo(true);

        TARJETAS.add(TARJETA);

        PERSONA.setId(1L);
        PERSONA.setTipoDoc("CC");
        PERSONA.setNumDoc("159786");
        PERSONA.setNombre("Cristian");
        PERSONA.setActivo(true);
        PERSONA.setTarjetas(TARJETAS);

        PERSONAS.add(PERSONA);

        TARJETA_DTO.setId(1L);
        TARJETA_DTO.setCapacidad(10000L);
        TARJETA_DTO.setActivo(true);
        TARJETA_DTO.setNumTarjeta("159456753");

    }


}
