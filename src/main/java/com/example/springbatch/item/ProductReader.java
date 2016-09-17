package com.example.springbatch.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.example.springbatch.domain.Product;

public class ProductReader implements ItemReader<Product> {
	
	
	private List<Product> productList;
	private Iterator<Product> iterator;
	
	public ProductReader() {
		productList = new ArrayList<>();
		Product product = new Product();
		product.setId(1);
		product.setName("Sample");
		productList.add(product);
		iterator = productList.iterator();
	}

	public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Product product = iterator.hasNext() == true ? iterator.next() : null;
		System.out.println("Reading product: " + product);
		return product;
	}

}
