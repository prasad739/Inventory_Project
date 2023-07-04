package com.prasad.product.Services.product;

import java.util.List;

import com.prasad.product.Entity.Product;


public interface IProductService {
	
	public Product addProduct(Product product);
	
	public Product updateProduct(int id,Product product);
	
	public String deleteProduct(int id);
	
	public Product getProduct(int id);
	
	public List<Product> viewAllProducts();
	
	public List<Product> viewProductsByCharges(float charges);
	
	public float getProductCharges(int id);
	
	public List<Product> viewProductsBySize(String size);
	
	//public Integer getProductId();
	
	

}
