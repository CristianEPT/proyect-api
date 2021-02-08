package co.com.organization.proyectoapi.web;

import co.com.organization.proyectoapi.commons.generic.PersonaDto;
import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonaWebApi {

    @Autowired
    private IPersonaService iPersonaService;

    @PostMapping
    public ResponseDto<PersonaDto> createPersona(@RequestBody @Valid PersonaDto persona) {
        return iPersonaService.createPersona(persona);
    }

    @GetMapping
    public ResponseDto<List<PersonaDto>> findAllPersonas() {
        return iPersonaService.findAllPersonas();
    }

    @PutMapping("/{idPersona}")
    public ResponseDto<PersonaDto> updatePersona(@PathVariable("idPersona") Long idPersona, @RequestBody PersonaDto personaDto) {
        return iPersonaService.updatePersona(idPersona, personaDto);
    }

    @GetMapping("/{idPersona}/tarjeta")
    public ResponseDto<List<TarjetaDto>> findAllTarjetas(@PathVariable("idPersona") Long idPersona) {
        return iPersonaService.findAllTarjetas(idPersona);
    }

    @PostMapping("/{idPersona}/tarjeta")
    public ResponseDto<TarjetaDto> createTarjeta(@PathVariable("idPersona") Long idPersona, @RequestBody @Valid TarjetaDto tarjetaDto) {
        return iPersonaService.createTarjeta(idPersona, tarjetaDto);
    }


}
