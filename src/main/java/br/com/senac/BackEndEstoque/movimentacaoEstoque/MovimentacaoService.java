package br.com.senac.BackEndEstoque.movimentacaoEstoque;

import br.com.senac.BackEndEstoque.exception.ClientRequestException;
import br.com.senac.BackEndEstoque.produto.Produto;
import br.com.senac.BackEndEstoque.produto.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoService produtoService;



    public MovimentacaoRepresentation.Retorno cadastrar(
            Integer produtoId,
            MovimentacaoRepresentation.Criar representation
    ) {
        return MovimentacaoRepresentation.Retorno
                .paraRetorno(
                        movimentacaoRepository.save(validaMovimentacao(produtoId, representation))
                );
    }


    public List<MovimentacaoRepresentation.Retorno> buscaPorProduto(Integer produtoId) {
        return MovimentacaoRepresentation.Retorno
                .paraListaDeRetorno(
                        movimentacaoRepository.findByProdutoId(produtoId)
                );
    }

    private MovimentacaoEstoque validaMovimentacao(
            Integer produtoId,
            MovimentacaoRepresentation.Criar representation
    ) {
        Produto produto = produtoService.buscaPeloId(produtoId);

        MovimentacaoEstoque.TipoMovimentacao tipo
                = MovimentacaoEstoque.TipoMovimentacao
                    .EncontraTipo(representation.getTipo())
                    .orElseThrow(() ->
                            new ClientRequestException("Tipo de movimentação não encontrado.")
                    );

        if (
                tipo.equals(MovimentacaoEstoque.TipoMovimentacao.SAIDA) &&
                produto.getQuantidade() < representation.getQuantidade()
        ) throw new ClientRequestException("Quantidade solicitada ultrapassa a de estoque.");

        return geraMovimentacao(produto, tipo, representation);
    }

    private MovimentacaoEstoque geraMovimentacao(
            Produto produto,
            MovimentacaoEstoque.TipoMovimentacao tipo,
            MovimentacaoRepresentation.Criar representation
    ) {
        produtoService.atualizarEstoque(produto, representation.getQuantidade(), tipo);
        return representation.transformaEmMovimentacao(produto, tipo);
    }

}
