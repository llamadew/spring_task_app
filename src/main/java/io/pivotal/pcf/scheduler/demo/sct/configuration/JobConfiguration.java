package io.pivotal.pcf.scheduler.demo.sct.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	private static final Log logger = LogFactory.getLog(JobConfiguration.class);
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job1() {
		return jobBuilderFactory.get("HelloJob")
				.start(stepBuilderFactory.get("SayHello")
					.tasklet(new Tasklet() {
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							logger.error("Hello Hello");
							return RepeatStatus.FINISHED;
						}
					})
					.build())
				.build();
	}

	@Bean
	public Job job2() {
		return jobBuilderFactory.get("GoodbyeJob")
				.start(stepBuilderFactory.get("SayGoodbye")
					.tasklet(new Tasklet() {
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							logger.error("I don't know why you say goodbye, I say hello");
							return RepeatStatus.FINISHED;
						}
					})
					.build())
				.build();
	}
}