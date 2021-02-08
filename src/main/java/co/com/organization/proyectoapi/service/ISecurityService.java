package co.com.organization.proyectoapi.service;

import co.com.organization.proyectoapi.commons.generic.LoginDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;

public interface ISecurityService {

    ResponseDto<LoginDto> login(LoginDto loginDto);

}
