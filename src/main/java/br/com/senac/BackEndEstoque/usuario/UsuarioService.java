package br.com.senac.BackEndEstoque.usuario;

import br.com.senac.BackEndEstoque.exception.ClientRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRepresentation.Retorno cadastrar(UsuarioRepresentation.Criar representacao) {
        return UsuarioRepresentation.Retorno
                .paraRetorno(
                        usuarioRepository.save(validacadastro(representacao))
                );
    }

    private Usuario validacadastro(UsuarioRepresentation.Criar representacao) {
        if (usuarioRepository.existsByLogin(representacao.getLogin()))
            throw new ClientRequestException("Login já existente.");
        return representacao.transformaEmUsuario();
    }

    public UsuarioRepresentation.Retorno logIn(UsuarioRepresentation.Login representacao) {
        Usuario usuario = usuarioRepository.findByLogin(representacao.getLogin())
                .orElseThrow(() -> new ClientRequestException("Login não cadastrado!"));

        if (!usuario.getSenha().equals(representacao.getSenha())) throw new ClientRequestException("Senha incorreta!");

        return UsuarioRepresentation.Retorno
                .paraRetorno(usuario);
    }
}
