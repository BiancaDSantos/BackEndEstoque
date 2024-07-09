package br.com.senac.BackEndEstoque.movimentacaoEstoque;

import br.com.senac.BackEndEstoque.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "movimentacao_estoque")
public class MovimentacaoEstoque {

    @Id
    @Column(name = "id_movimentacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipo;

    @NotNull(message = "O campo quantidade não pode ser nulo.")
    @Column(name = "qt_movimentacao")
    private Integer quantidade;

    @NotNull(message = "O campo produto não pode ser nulo.")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produto.class)
    @JoinColumn(name = "id_produto")
    private Produto produto;


    @Getter
    public enum TipoMovimentacao {
        ENTRADA("Entrada", "E", 1),
        SAIDA("Saída", "S", -1);

        public final String nome;
        public final String abreviacao;
        public final Integer multiplicador;

        TipoMovimentacao(String nome, String abreviacao, Integer multiplicador) {
            this.nome = nome;
            this.abreviacao = abreviacao;
            this.multiplicador = multiplicador;
        }

        public static Optional<TipoMovimentacao> EncontraTipo(String filtro) {
            for (TipoMovimentacao tipo: TipoMovimentacao.values()) {
                if (tipo.abreviacao.equals(filtro)) return Optional.of(tipo);
            }
            return Optional.empty();
        }

        @Override
        public String toString() {
            return this.abreviacao;
        }
    }
}
