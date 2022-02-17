package com.example_tentnando.produto.service;

import com.example_tentnando.produto.entitie.Produto;
import com.example_tentnando.produto.reposittory.RepositoryProduto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceProduto {
    final RepositoryProduto repositoryProduto;
        public ServiceProduto(RepositoryProduto repositoryProduto){
            this.repositoryProduto = repositoryProduto;
        }
        @Transactional
        public Produto saveprod(Produto prod){
            return repositoryProduto.save(prod);
        }

    public List<Produto> findall() {
            return repositoryProduto.findAll();
    }
    public Optional<Produto> findbyid(UUID id){
            return repositoryProduto.findById(id);
    }

    public void delete(Produto produto) {
            repositoryProduto.delete(produto);
    }
}
