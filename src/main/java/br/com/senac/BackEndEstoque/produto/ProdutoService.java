package br.com.senac.BackEndEstoque.produto;

import br.com.senac.BackEndEstoque.exception.ClientRequestException;
import br.com.senac.BackEndEstoque.movimentacaoEstoque.MovimentacaoEstoque;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto cadastrar(ProdutoRepresentation.Criar representacao) {
        return produtoRepository.save(validaCadastro(representacao));
    }

    public Produto atualizar(
            Integer id,
            ProdutoRepresentation.Atualizar representacao
    ) {
        return produtoRepository.save(validaAtualizacao(id, representacao));
    }

    public Produto buscaPeloId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ClientRequestException("Produto não encontrado."));
    }

    public void desativar(Integer id) {
        Produto produto = buscaPeloId(id);
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    private Produto validaCadastro(ProdutoRepresentation.Criar representacao) {
        if (produtoRepository.existsByEAN(representacao.getEAN()))
            throw new ClientRequestException("EAN já existente.");
        return representacao.transformaEmProduto();
    }

    private Produto validaAtualizacao(
            Integer id,
            ProdutoRepresentation.Atualizar representacao
    ) {
        if (produtoRepository.existsByEANAndIdNot(representacao.getEAN(), id))
            throw new ClientRequestException("EAN já existente.");
        return representacao.atualizaProduto(buscaPeloId(id));
    }

    public List<Produto> buscarVarios() {
        return produtoRepository.findAll();
    }

    public void atualizarEstoque(
            Produto produto,
            Integer quantidade,
            MovimentacaoEstoque.TipoMovimentacao tipo
    ) {
        produto.setQuantidade(produto.getQuantidade() + quantidade * tipo.multiplicador);
        produtoRepository.save(produto);
    }
}
