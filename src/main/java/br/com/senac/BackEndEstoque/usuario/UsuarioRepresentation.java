package br.com.senac.BackEndEstoque.usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

public interface UsuarioRepresentation {

    @Data
    class Criar {
        @NotNull(message = "O campo nome não pode ser nulo.")
        @Size(
                max = 100,
                message = "O campo nome não pode ter mais que 100 caracteres."
        )
        @Size(
                min = 3,
                message = "O campo nome não pode ter menos que 3 caracteres."
        )
        private String nome;

        @NotNull(message = "O campo email não pode ser nulo.")
        @Size(
                max = 100,
                message = "O campo email não pode ter mais que 100 caracteres."
        )
        @Size(
                min = 3,
                message = "O campo email não pode ter menos que 3 caracteres."
        )
        private String email;

        @NotNull(message = "O campo login não pode ser nulo.")
        @Size(
                max = 20,
                message = "O campo login não pode ter mais que 20 caracteres."
        )
        @Size(
                min = 5,
                message = "O campo login não pode ter menos que 5 caracteres."
        )
        private String login;

        @NotNull(message = "O campo senha não pode ser nulo.")
        @Size(
                max = 10,
                message = "O campo senha não pode ter mais que 10 caracteres."
        )
        @Size(
                min = 6,
                message = "O campo senha não pode ter menos que 6 caracteres."
        )
        private String senha;

        public Usuario transformaEmUsuario() {
            return Usuario.builder()
                    .nome(this.nome)
                    .email(this.email)
                    .login(this.login)
                    .senha(this.senha)
                    .build();
        }
    }

    @Data
    class Login {

        @NotNull(message = "O campo login não pode ser nulo.")
        private String login;

        @NotNull(message = "O campo senha não pode ser nulo.")
        private String senha;
    }

    @Data
    @Builder
    class Retorno {
        private Integer id;
        private String nome;
        private String email;
        private String login;

        public static Retorno paraRetorno(Usuario usuario) {
            return Retorno.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .email(usuario.getEmail())
                    .login(usuario.getLogin())
                    .build();
        }
    }
}
