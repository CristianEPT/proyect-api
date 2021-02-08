package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.PersonaDto;
import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Persona;
import co.com.organization.proyectoapi.persistence.entities.Tarjeta;
import co.com.organization.proyectoapi.persistence.repositories.IPersonaRepository;
import co.com.organization.proyectoapi.persistence.repositories.ITarjetaRepository;
import co.com.organization.proyectoapi.service.IPersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersonaRepository iPersonaRepository;

    @Autowired
    private ITarjetaRepository iTarjetaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDto<PersonaDto> createPersona(PersonaDto personaDto) {

        Persona persona = new Persona();
        personaDto.setActivo(true);
        modelMapper.map(personaDto, persona);
        iPersonaRepository.save(persona);
        return ResponseDto.<PersonaDto>builder()
                .message("Creado correctamente")
                .status("OK")
                .code("0000")
                .data(personaDto)
                .build();

    }

    @Override
    public ResponseDto<List<PersonaDto>> findAllPersonas() {
        String mensaje = "Registros no encontrados";
        List<PersonaDto> personas = iPersonaRepository.findAll()
                .stream()
                .map(persona -> modelMapper.map(persona, PersonaDto.class)).collect(Collectors.toList());
        if (!personas.isEmpty()) {
            mensaje = "Registros no encontrados";
        }
        return ResponseDto.<List<PersonaDto>>builder()
                .data(personas)
                .message(mensaje)
                .status("OK")
                .code("0000")
                .build();
    }

    @Override
    public ResponseDto<PersonaDto> updatePersona(Long idPersona, PersonaDto personaDto) {
        Optional<Persona> optionalPersona = iPersonaRepository.findPersonaById(idPersona);
        String mensaje = "Persona no encontrada";
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            if (!StringUtils.isEmpty(personaDto.getNombre())) {
                persona.setNombre(personaDto.getNombre());
            }
            if (!StringUtils.isEmpty(personaDto.getTipoDoc())) {
                persona.setTipoDoc(personaDto.getTipoDoc());
            }
            if (!StringUtils.isEmpty(personaDto.getNumDoc())) {
                persona.setNumDoc(personaDto.getNumDoc());
            }
            if (!StringUtils.isEmpty(personaDto.getActivo())) {
                persona.setActivo(personaDto.getActivo());
            }
            iPersonaRepository.save(persona);
            modelMapper.map(persona, personaDto);
            mensaje = "Registro actualizados correctamente";
        }
        return ResponseDto.<PersonaDto>builder()
                .data(personaDto)
                .message(mensaje)
                .status("OK")
                .code("0000")
                .build();
    }

    @Override
    public ResponseDto<List<TarjetaDto>> findAllTarjetas(Long idPersona) {
        Optional<Persona> optionalPersona = iPersonaRepository.findPersonaById(idPersona);
        List<TarjetaDto> tarjetas = new ArrayList<>();
        String mensaje = "Persona no encontrada";
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            tarjetas = persona.getTarjetas().stream().map(tarjeta -> modelMapper.map(tarjeta, TarjetaDto.class)).collect(Collectors.toList());
            mensaje = "Registros entcontrados";
        }
        return ResponseDto.<List<TarjetaDto>>builder()
                .data(tarjetas)
                .message(mensaje)
                .status("OK")
                .code("0000")
                .build();
    }

    @Override
    public ResponseDto<TarjetaDto> createTarjeta(Long idPersona, TarjetaDto tarjetaDto) {
        Optional<Persona> optionalPersona = iPersonaRepository.findPersonaById(idPersona);
        String mensaje = "Persona no encontrada";
        if (optionalPersona.isPresent()) {
            Tarjeta tarjeta = new Tarjeta();
            tarjetaDto.setActivo(true);
            modelMapper.map(tarjetaDto, tarjeta);
            tarjeta.setPersona(optionalPersona.get());
            iTarjetaRepository.save(tarjeta);
            mensaje = "Tarjeta creada correctamente";
        }
        return ResponseDto.<TarjetaDto>builder()
                .data(tarjetaDto)
                .message(mensaje)
                .status("OK")
                .code("0000")
                .build();
    }

}
