package com.boot.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
public class DetailJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음


    @Bean
    public Job DetailJob() {
        return jobBuilderFactory.get("DetailJob")
                .start(DataCollect())
                .next(DataPreprocess())
                .next(DataLoad())
                .build();
    }

    @Bean
    public Step DataCollect() {
        return stepBuilderFactory.get("DataCollect")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step DataCollect");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step DataPreprocess() {
        return stepBuilderFactory.get("DataPreprocess")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step DataPreprocess");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step DataLoad() {
        return stepBuilderFactory.get("DataLoad")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step DataLoad");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
