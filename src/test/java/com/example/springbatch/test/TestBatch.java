package com.example.springbatch.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springbatch.config.TestBatchConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestBatchConfig.class })
public class TestBatch {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testStepExecution() {
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step-under-test");

		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}

}
