package br.com.senac.BackEndEstoque.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByEAN(String EAN);

    boolean existsByEANAndIdNot(String EAN, Integer id);
}
