package co.com.organization.proyectoapi.service;

import co.com.organization.proyectoapi.commons.generic.PersonaDto;
import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;

import java.util.List;

public interface IPersonaService {

    ResponseDto<PersonaDto> createPersona(PersonaDto personaDto);

    ResponseDto<List<PersonaDto>> findAllPersonas();

    ResponseDto<PersonaDto> updatePersona(Long idPersona, PersonaDto personaDto);

    ResponseDto<List<TarjetaDto>> findAllTarjetas(Long idPersona);

    ResponseDto<TarjetaDto> createTarjeta(Long idPersona, TarjetaDto tarjetaDto);

}
