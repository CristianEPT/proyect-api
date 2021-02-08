package co.com.organization.proyectoapi.commons.generic;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginDto {

    @NotEmpty
    @NotNull
    private String user;
    @NotEmpty
    @NotNull
    private String pass;
    private String status;
    private String token;

}
