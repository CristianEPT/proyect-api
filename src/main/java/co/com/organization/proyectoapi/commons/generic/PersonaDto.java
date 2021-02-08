package co.com.organization.proyectoapi.commons.generic;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonaDto {

    private Long id;
    @NotEmpty
    @NotNull
    private String nombre;
    @NotEmpty
    @NotNull
    private String tipoDoc;
    @NotEmpty
    @NotNull
    private String numDoc;
    private Boolean activo;

}
