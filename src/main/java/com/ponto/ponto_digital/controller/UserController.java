package com.ponto.ponto_digital.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.ponto_digital.business.UsuarioBusiness;
import com.ponto.ponto_digital.exception.PontoExceptionController;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.model.dto.UsuarioView;
import com.ponto.ponto_digital.utils.CustomPageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "Modulo de Usuário")
public class UserController {

    private final UsuarioBusiness usuarioBusiness;

    @Autowired
    public UserController(UsuarioBusiness usuarioBusiness) {
        this.usuarioBusiness = usuarioBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),

    })
    @Operation(summary = "Endpoint responsável por realizar de Usuário")
    @GetMapping
    public ResponseEntity<UsuarioView> findByUsuario(@RequestParam(name = "id") final UUID id) {

        UsuarioView response = usuarioBusiness.findByUsuario(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
    })
    @Operation(summary = "Endpoint responsável por deletar usuario")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") final UUID id) {

        usuarioBusiness.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
    })
    @Operation(summary = "Endpoint reponsável por buscar todas as transações")
    @GetMapping("find-pontos")
    public ResponseEntity<Page<MarcarPonto>> findAllUsuarios(
            @RequestParam(name = "id") final UUID id,
            @RequestParam(value = "page", required = false) final Integer page,
            @RequestParam(value = "size", required = false) final Integer size,
            @RequestParam(value = "sorting", required = false) final String sorting) {

        Page<MarcarPonto> response = usuarioBusiness.findallPonto(id,CustomPageable.getInstance(page, size, sorting));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
