package com.example_tentnando.produto.reposittory;

import com.example_tentnando.produto.entitie.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryProduto extends JpaRepository<Produto, UUID> {
}
