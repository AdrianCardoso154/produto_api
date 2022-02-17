package com.example_tentnando.produto.controller;

import com.example_tentnando.produto.dtos.Produtodto;
import com.example_tentnando.produto.entitie.Produto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example_tentnando.produto.service.ServiceProduto;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/crud_produto")
public class ControllerProduto {
    final ServiceProduto serviceProduto;
        public ControllerProduto(ServiceProduto serviceProduto){
            this.serviceProduto = serviceProduto;
        }

        @PostMapping
        public ResponseEntity<Object> saveProduto(@RequestBody Produtodto proddto){
            var prodpass = new Produto();
            BeanUtils.copyProperties(proddto, prodpass);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceProduto.saveprod(prodpass));
        }
        @GetMapping
            public ResponseEntity<Object> getallProduto(){
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceProduto.findall());
        }
        @GetMapping("/{id}")
            public ResponseEntity<Object> getOne(@PathVariable(value = "id")UUID id){
            Optional produtoOptional = serviceProduto.findbyid(id);//o retorno da busca e direcionada a classe Optional
            if(!produtoOptional.isPresent()){//verifica se ha um id correspondente no banco
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não enccontrado");
            }
            return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
        }

        @DeleteMapping("/{id}")
            public ResponseEntity<Object> delete(@PathVariable(value = "id")UUID id){
            Optional<Produto> produtoOptional = serviceProduto.findbyid(id);
            if (!produtoOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não enccontrado");
            }
            serviceProduto.delete(produtoOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Produto apagado com sucesso");
        }

        @PutMapping("/{id}")
            public ResponseEntity<Object> updateProd(@PathVariable(value = "id")UUID id,
                                                 @RequestBody Produtodto produtodto){
            Optional<Produto> produtoOptional = serviceProduto.findbyid(id);
            if (!produtoOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não enccontrado");
            }
            var produtoup = new Produto();
            BeanUtils.copyProperties(produtodto, produtoup);
            produtoup.setId(produtoOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(serviceProduto.saveprod(produtoup));
        }

}
