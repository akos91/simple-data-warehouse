package com.interview.simpledatawarehouse.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.interview.simpledatawarehouse.model.AdvertismentStat;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Value("${simple.data.warehouse.input.csv.file}")
    private String inputCsvFile;

    @Bean
    public BeanWrapperFieldSetMapper<AdvertismentStat> beanWrapperFieldSetMapper() {
        BeanWrapperFieldSetMapper<AdvertismentStat> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapperCustom<>();
        beanWrapperFieldSetMapper.setTargetType(AdvertismentStat.class);
        return beanWrapperFieldSetMapper;
    }

    @Bean
    public FlatFileItemReader<AdvertismentStat> reader() {
        return new FlatFileItemReaderBuilder<AdvertismentStat>().name("advertismentStatsCsvItemReader")
                .resource(new ClassPathResource(inputCsvFile)).delimited()
                .names("Datasource", "Campaign", "Daily", "Clicks", "Impressions").linesToSkip(1)
                .fieldSetMapper(beanWrapperFieldSetMapper()).build();
    }

    @Bean
    public JdbcBatchItemWriter<AdvertismentStat> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AdvertismentStat>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO ADVERTISEMENTSTATS (datasource, campaign, daily, clicks, impressions) VALUES (:datasource, :campaign, :daily, :clicks, :impressions)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory, JobCompletionNotificationListener listener,
            Step step1) {
        return jobBuilderFactory.get("importAdvertismentStatsJob").incrementer(new RunIdIncrementer())
                .listener(listener).flow(step1).end().build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<AdvertismentStat> writer, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("step1").<AdvertismentStat, AdvertismentStat> chunk(10).reader(reader())
                .writer(writer).build();
    }
}
