package br.com.senac.BackEndEstoque.produto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "produto")
public class Produto {

    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_produto")
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

    @Column(name = "descricao_produto")
    @NotNull(message = "O campo descricao não pode ser nulo.")
    @Size(
            max = 200,
            message = "O campo descricao não pode ter mais que 200 caracteres."
    )
    @Size(
            min = 10,
            message = "O campo descricao não pode ter menos que 10 caracteres."
    )
    private String descricao;

    @Column(name = "ean")
    @NotNull(message = "O campo EAN não pode ser nulo.")
    @Size(
            max = 13,
            min = 13,
            message = "O campo EAN deve ter 13 caracteres."
    )
    private String EAN;

    @Column(name = "qt_produto")
    @NotNull(message = "O campo quantidade não pode ser nulo.")
    private Integer quantidade;

    @Column(name = "valor")
    @NotNull(message = "O campo valor não pode ser nulo.")
    private Double valor;

    @Column(name = "ativo")
    private Boolean ativo;
}
