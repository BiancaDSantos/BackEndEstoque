package br.com.senac.BackEndEstoque.produto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public interface ProdutoRepresentation {

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

        @NotNull(message = "O campo EAN não pode ser nulo.")
        @Size(
                max = 13,
                min = 13,
                message = "O campo EAN deve ter 13 caracteres."
        )
        private String EAN;

        @NotNull(message = "O campo valor não pode ser nulo.")
        private Double valor;

        public Produto transformaEmProduto() {
            return Produto.builder()
                    .nome(this.nome)
                    .descricao(this.descricao)
                    .EAN(this.EAN)
                    .valor(this.valor)
                    .quantidade(0)
                    .ativo(true)
                    .build();
        }
    }



    @Data
    class Atualizar {
        @Size(
                max = 100,
                message = "O campo nome não pode ter mais que 100 caracteres."
        )
        @Size(
                min = 3,
                message = "O campo nome não pode ter menos que 3 caracteres."
        )
        private String nome;

        @Size(
                max = 200,
                message = "O campo descricao não pode ter mais que 200 caracteres."
        )
        @Size(
                min = 10,
                message = "O campo descricao não pode ter menos que 10 caracteres."
        )
        private String descricao;

        @Size(
                max = 13,
                min = 13,
                message = "O campo EAN deve ter 13 caracteres."
        )
        private String EAN;

        private Double valor;

        public Produto atualizaProduto(Produto produto) {
            if (this.nome != null) produto.setNome(this.nome);
            if (this.descricao != null) produto.setDescricao(this.descricao);
            if (this.EAN != null) produto.setEAN(this.EAN);
            if (this.valor != null) produto.setValor(this.valor);

            return produto;
        }
    }

//    class retornoDetalhe
//    class retornoLista
}