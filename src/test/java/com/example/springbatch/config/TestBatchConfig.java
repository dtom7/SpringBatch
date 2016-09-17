package com.example.springbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.example.springbatch.domain.Product;
import com.example.springbatch.item.ProductReader;
import com.example.springbatch.item.ProductWriter;

@EnableBatchProcessing
@Configuration
public class TestBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-hsqldb.sql")
				.addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql")
				.setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public Job jobUnderTest() {
		return jobBuilderFactory.get("job-under-test").start(stepUnderTest()).build();
	}

	@Bean
	public Step stepUnderTest() {
		return stepBuilderFactory.get("step-under-test").<Product, Product>chunk(1).reader(reader()).writer(writer()).build();
	}

	@Bean
	@StepScope
	public ItemReader<Product> reader() {
		return new ProductReader();
	}

	@Bean
	@StepScope
	public ItemWriter<Product> writer() {
		return new ProductWriter();
	}

}
