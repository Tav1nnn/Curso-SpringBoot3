package br.com.otavio.CursoSpringBoot3.controllers;

import br.com.otavio.CursoSpringBoot3.services.AuthService;
import br.com.otavio.CursoSpringBoot3.security.AccountCredentialsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user  ans returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity singin(@RequestBody AccountCredentialsVO data) {

        if (data == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        var token = authService.signin(data);

        if(token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        return ResponseEntity.ok().body(token);
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {

        if (username == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        var token = authService.refreshToken(username, refreshToken);

        if(token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
        }

        return token;
    }
}
