package br.com.otavio.CursoSpringBoot3.controllers;

import br.com.otavio.CursoSpringBoot3.services.AuthService;
import br.com.otavio.CursoSpringBoot3.vo.v1.AccountCredentialsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user  ans returns a token")
    @PostMapping(value = "/singin")
    public ResponseEntity singin(@RequestBody AccountCredentialsVO data) {

        if (data == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        var token = authService.signin(data);

        if(token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        return token;
    }

}
