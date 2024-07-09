package br.com.senac.BackEndEstoque.movimentacaoEstoque;

import br.com.senac.BackEndEstoque.produto.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public interface MovimentacaoRepresentation {

    @Data
    class Criar {

        @NotNull(message = "O campo tipo não pode ser nulo.")
        private String tipo;

        @NotNull(message = "O campo quantidade não pode ser nulo.")
        @Min(value = 1, message = "Quantidade movimentada deve ser possitiva.")
        private Integer quantidade;

        public MovimentacaoEstoque transformaEmMovimentacao(
                Produto produto,
                MovimentacaoEstoque.TipoMovimentacao tipoMovimentacao
        ) {
            return MovimentacaoEstoque.builder()
                    .quantidade(this.quantidade)
                    .tipo(tipoMovimentacao)
                    .produto(produto)
                    .build();
        }
    }

    @Data
    @Builder
    class Retorno {
        private Integer id;
        private String tipo;
        private Integer quantidade;
        private Integer quantidadeMovimentada;
        private Integer produtoId;

        public static Retorno paraRetorno(MovimentacaoEstoque movimentacaoEstoque) {
            return Retorno.builder()
                    .id(movimentacaoEstoque.getId())
                    .tipo(movimentacaoEstoque.getTipo().getNome())
                    .quantidade(movimentacaoEstoque.getQuantidade())
                    .quantidadeMovimentada(
                            movimentacaoEstoque.getQuantidade() * movimentacaoEstoque.getTipo().getMultiplicador()
                    )
                    .produtoId(movimentacaoEstoque.getProduto().getId())
                    .build();
        }

        public static List<Retorno> paraListaDeRetorno(List<MovimentacaoEstoque> movimentacoes) {
            return movimentacoes.stream().map(Retorno::paraRetorno).toList();
        }
    }
}
