package com.ponto.ponto_digital.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ponto.ponto_digital.business.MarcarPontoBusiness;
import com.ponto.ponto_digital.exception.PontoExceptionController;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.utils.CustomPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("ponto")
@Tag(name = "Modulo de Pontos")
public class MarcaPontoController {
    private final MarcarPontoBusiness pontoBusiness;

    @Autowired
    public MarcaPontoController(MarcarPontoBusiness pontoBusiness) {
        this.pontoBusiness = pontoBusiness;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(schema = @Schema(implementation = PontoExceptionController.ErrorHandling.class)) }),
    })
    @Operation(summary = "Endpoint reponsável por bater o ponto")
    @PostMapping()
    public ResponseEntity<MarcarPonto> savarConta(@RequestBody MarcarPonto marcarPonto,
            @RequestParam(name = "usuario_id") final UUID usuario_id) {
        MarcarPonto pontoSalvo = pontoBusiness.baterPonto(marcarPonto, usuario_id);

        return ResponseEntity.status(HttpStatus.OK).body(pontoSalvo);
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
    @GetMapping("find-all")
    public ResponseEntity<Page<MarcarPonto>> findAllUsuarios(
            @RequestParam(value = "page", required = false) final Integer page,
            @RequestParam(value = "size", required = false) final Integer size,
            @RequestParam(value = "sorting", required = false) final String sorting) {

        Page<MarcarPonto> response = pontoBusiness.findAllPontos(CustomPageable.getInstance(page, size, sorting));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
