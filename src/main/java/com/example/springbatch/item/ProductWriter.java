package com.example.springbatch.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.example.springbatch.domain.Product;

public class ProductWriter implements ItemWriter<Product> {

	@Override
	public void write(List<? extends Product> productList) throws Exception {
		if (productList != null && !productList.isEmpty()) {
			System.out.println("Writing product: " + productList.get(0));
		}
	}

}
