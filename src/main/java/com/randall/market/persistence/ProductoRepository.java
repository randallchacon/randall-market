package com.randall.market.persistence;

import com.randall.market.persistence.crud.ProductoCrudRepository;
import com.randall.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;
    public List<Producto> getAll(){ return (List<Producto>) productoCrudRepository.findAll();}
    public List<Producto> getByCategoria(int idCategoria){ return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);}
    public Optional<List<Producto>> getEscasos(int cantidad){ return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);}
}
