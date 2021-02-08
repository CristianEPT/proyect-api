package co.com.organization.proyectoapi.web;

import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarjeta")
public class TarjetaWebApi {

    @Autowired
    private ITarjetaService iTarjetaService;

    @PutMapping("/{idTarjeta}")
    public ResponseDto<TarjetaDto> updatePersona(@PathVariable("idTarjeta") Long idTarjeta, @RequestBody TarjetaDto tarjetaDto) {
        return iTarjetaService.updateTarjeta(idTarjeta, tarjetaDto);
    }

}
