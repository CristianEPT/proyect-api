package co.com.organization.proyectoapi.service.impl;

import co.com.organization.proyectoapi.commons.generic.TarjetaDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.persistence.entities.Tarjeta;
import co.com.organization.proyectoapi.persistence.repositories.ITarjetaRepository;
import co.com.organization.proyectoapi.service.ITarjetaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class TarjetaService implements ITarjetaService {

    @Autowired
    private ITarjetaRepository iTarjetaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDto<TarjetaDto> updateTarjeta(Long idTarjeta, TarjetaDto tarjetaDto) {
        Optional<Tarjeta> tarjetaOptional = iTarjetaRepository.findTarjetaById(idTarjeta);
        String mensaje = "Tarjeta no encontrada";
        if (tarjetaOptional.isPresent()) {
            Tarjeta tarjeta = tarjetaOptional.get();
            if (!StringUtils.isEmpty(tarjetaDto.getActivo())) {
                tarjeta.setActivo(tarjetaDto.getActivo());
            }
            if (!StringUtils.isEmpty(tarjetaDto.getCapacidad())) {
                tarjeta.setCapacidad(tarjetaDto.getCapacidad());
            }
            iTarjetaRepository.save(tarjeta);
            modelMapper.map(tarjeta, tarjetaDto);
            mensaje = "Tarjeta actualizada correctamente";
        }
        return ResponseDto.<TarjetaDto>builder()
                .code("0000")
                .status("OK")
                .message(mensaje)
                .data(tarjetaDto)
                .build();
    }
}
