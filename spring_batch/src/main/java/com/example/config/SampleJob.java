package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.lisener.FirstJobListener;
import com.example.service.SecondTasklet;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	//@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First job").incrementer(new RunIdIncrementer()).start(firststep())
				.next(secondstep()).listener(firstJobListener).build();
	}
	
	private Step firststep() {
		return stepBuilderFactory.get("First step").tasklet(firstTask()).build();
	}
	
	private Tasklet firstTask() {
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("inside first tasklet");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	private Step secondstep() {
		return stepBuilderFactory.get("Second step").tasklet(secondTasklet).build();
	}
	
	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("second job").incrementer(new RunIdIncrementer()).build();
		
	}
	
	/*
	 * private Tasklet secondTask() { return new Tasklet() {
	 * 
	 * @Override public RepeatStatus execute(StepContribution contribution,
	 * ChunkContext chunkContext) throws Exception {
	 * System.out.println("inside second tasklet"); return RepeatStatus.FINISHED; }
	 * }; }
	 */
}
