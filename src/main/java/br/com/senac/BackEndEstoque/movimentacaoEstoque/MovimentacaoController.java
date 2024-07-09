package br.com.senac.BackEndEstoque.movimentacaoEstoque;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto/{produtoId}/movimentacao")
@CrossOrigin("*")
@AllArgsConstructor
public class MovimentacaoController{

    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoRepresentation.Retorno> cadastrar(
            @PathVariable("produtoId") Integer produtoId,
            @Valid @RequestBody MovimentacaoRepresentation.Criar representacao
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimentacaoService.cadastrar(produtoId, representacao));
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoRepresentation.Retorno>> buscaPorProduto(
            @PathVariable("produtoId") Integer produtoId
    ) {
        return ResponseEntity.ok(movimentacaoService.buscaPorProduto(produtoId));
    }
}
