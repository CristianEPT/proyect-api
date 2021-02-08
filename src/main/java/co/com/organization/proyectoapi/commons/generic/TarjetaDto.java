package co.com.organization.proyectoapi.commons.generic;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TarjetaDto {

    private Long id;
    @NotNull
    @NotEmpty
    private String numTarjeta;
    private Boolean activo;
    private Long capacidad;

}
