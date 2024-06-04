package com.example.lisener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("jobExecution get name:"+jobExecution.getJobConfigurationName());
		jobExecution.getExecutionContext().put("key", "valueee");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("jobExecution after:"+jobExecution.getJobInstance().getJobName());
	}

	
}
