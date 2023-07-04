package com.prasad.product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.product.Entity.Product;
import com.prasad.product.Services.product.ProductServiceImpl;

@RestController
public class ProductController {

	@Autowired
	ProductServiceImpl impl;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> saveProduct( Product product) {
		Product save = impl.addProduct(product);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable int id){
		Product save = impl.updateProduct(id, product);
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		String save = impl.deleteProduct(id);
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@GetMapping("/viewProduct/{id}")
	public ResponseEntity<Product> viewProduct(@PathVariable int id){
		Product save = impl.getProduct(id);
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> viewAllProducts(){
		List<Product> save = impl.viewAllProducts();
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@GetMapping("/getProductsByChrges/{charges}")
	public ResponseEntity<List<Product>> viewProductsByChrges(@PathVariable float charges){
		List<Product> save = impl.viewProductsByCharges(charges);
		return new ResponseEntity<>(save,HttpStatus.OK);
		
	}
	
	@GetMapping("/getProductChrges/{id}")
	public ResponseEntity<Float> getProductCharges(@PathVariable int id){
		float charges = impl.getProductCharges(id);
		return new ResponseEntity<>(charges,HttpStatus.CREATED);
		
	}

}
