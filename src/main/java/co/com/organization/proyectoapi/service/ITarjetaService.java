package co.com.organization.proyectoapi.service;

import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;

public interface ITarjetaService {

    ResponseDto<TarjetaDto> updateTarjeta(Long idTarjeta, TarjetaDto tarjetaDto);

}
