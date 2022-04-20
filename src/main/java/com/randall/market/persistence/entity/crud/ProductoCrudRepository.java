package com.randall.market.persistence.entity.crud;

import com.randall.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> { //define la clase y el tipo de dato del PK
}
