package br.com.senac.BackEndEstoque.movimentacaoEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Integer> {
    List<MovimentacaoEstoque> findByProdutoId(Integer produtoId);
}
