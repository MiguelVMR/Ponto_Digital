package com.ponto.ponto_digital.controller;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ponto.ponto_digital.business.AutenticacaoBusiness;
import com.ponto.ponto_digital.exception.PontoExceptionController;
import com.ponto.ponto_digital.model.dto.Usuario;
import com.ponto.ponto_digital.model.record.LoginRecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Modulo de Autenticação")
public class AuthController {

    private final AutenticacaoBusiness autenticacaoBusiness;

    @Autowired
    public AuthController(AutenticacaoBusiness autenticacaoBusiness) {
        this.autenticacaoBusiness = autenticacaoBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
    })
    @Operation(summary = "Endpoint responsável por realizar a criação de usuario")
    @PostMapping("create-user")
    public ResponseEntity<String> save(
            @RequestBody @Valid final Usuario usuario) {

        autenticacaoBusiness.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Criado");
    }

      @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
                    {@Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class))}),
    })
    @Operation(summary = "Endpoint responsável por realizar o login do usuário")
    @PostMapping("login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRecord loginRecord) {

        AccessTokenResponse response = autenticacaoBusiness.login(loginRecord);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
