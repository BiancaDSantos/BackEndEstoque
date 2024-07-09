package br.com.senac.BackEndEstoque.produto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProdutoRepresentation.Atualizar representacao
    ) {
        return ResponseEntity
                .ok(produtoService.atualizar(id, representacao));
    }

    @DeleteMapping("/{id}")
    public void desativar(
            @PathVariable Integer id
    ) {
        produtoService.desativar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarUm(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(produtoService.buscaPeloId(id));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarVarios() {
        return ResponseEntity.ok(produtoService.buscarVarios());
    }
}
