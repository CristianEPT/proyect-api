package co.com.organization.proyectoapi.web;

import co.com.organization.proyectoapi.commons.generic.LoginDto;
import co.com.organization.proyectoapi.commons.responses.ResponseDto;
import co.com.organization.proyectoapi.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("security")
public class SecurityWebApi {

    @Autowired
    private ISecurityService iSecurityService;

    @PostMapping("/login")
    public ResponseDto<LoginDto> login(@RequestBody @Valid LoginDto loginDto) {
        return iSecurityService.login(loginDto);
    }

}
