package br.com.senac.BackEndEstoque.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity(name = "usuario")
@Data
@Builder
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
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

    @Column(name = "email")
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

    @Column(name = "login")
    @NotNull(message = "O campo login não pode ser nulo.")
    @Size(
            max = 20,
            message = "O campo login não pode ter mais que 100 caracteres."
    )
    @Size(
            min = 15,
            message = "O campo nome não pode ter menos que 3 caracteres."
    )
    private String login;

    @Column(name = "senha")
    @NotNull(message = "O campo senha não pode ser nulo.")
    @Size(
            max = 30,
            message = "O campo nome não pode ter mais que 100 caracteres."
    )
    @Size(
            min = 15,
            message = "O campo nome não pode ter menos que 3 caracteres."
    )
    private String senha;
}
