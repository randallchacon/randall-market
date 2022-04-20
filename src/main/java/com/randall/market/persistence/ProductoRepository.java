package com.randall.market.persistence;

import com.randall.market.persistence.crud.ProductoCrudRepository;
import com.randall.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

}
