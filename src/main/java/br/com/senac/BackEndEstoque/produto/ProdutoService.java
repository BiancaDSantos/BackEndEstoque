package br.com.senac.BackEndEstoque.produto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public Produto cadastrar(ProdutoRepresentation.Criar representacao) {
        return produtoRepository.save(validaCadastro(representacao));
    }

    private Produto validaCadastro(ProdutoRepresentation.Criar representacao) {
        //valida se o EAN já existe
        //valida outra coisa
        return representacao.transformaEmProduto();
    }
}
