package co.com.organization.proyectoapi.commons.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String code;
    private String message;
    private T data;

}
