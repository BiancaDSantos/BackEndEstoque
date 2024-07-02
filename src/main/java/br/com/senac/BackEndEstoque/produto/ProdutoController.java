package br.com.senac.BackEndEstoque.produto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(
            @Valid @RequestBody ProdutoRepresentation.Criar representacao
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.cadastrar(representacao));
    }
}
