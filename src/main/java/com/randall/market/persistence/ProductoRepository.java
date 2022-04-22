package com.randall.market.persistence;

import com.randall.market.domain.Product;
import com.randall.market.domain.repository.ProductRepository;
import com.randall.market.persistence.crud.ProductoCrudRepository;
import com.randall.market.persistence.entity.Producto;
import com.randall.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //@Component is also valid, but is more general than repository
public class ProductoRepository implements ProductRepository {
    @Autowired //debo estar seguro que el objeto a inyectar es un componente de spring
    private ProductoCrudRepository productoCrudRepository;
    @Autowired //inyeccion de dependencias con spring
    private ProductMapper mapper;
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProductos(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
