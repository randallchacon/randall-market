package com.randall.market.web.controller;

import com.randall.market.domain.Product;
import com.randall.market.domain.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.spi.MidiDeviceProvider;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=404, message = "Producto not found"),
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the producto", required = true, example = "7")
                                              @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /*public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }*/

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /*public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }*/

    @PostMapping("/save")
    public ResponseEntity<Product> save (@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    /*public Product save(@RequestBody Product product) {
        return productService.save(product);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    /*public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }*/
}
