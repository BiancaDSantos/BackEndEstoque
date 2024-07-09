package br.com.senac.BackEndEstoque.usuario;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioRepresentation.Retorno> cadastrar(
            @Valid @RequestBody UsuarioRepresentation.Criar representacao
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.cadastrar(representacao));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioRepresentation.Retorno> login(
            @Valid @RequestBody UsuarioRepresentation.Login representacao
    ) {
        return ResponseEntity.ok(usuarioService.logIn(representacao));
    }

}
