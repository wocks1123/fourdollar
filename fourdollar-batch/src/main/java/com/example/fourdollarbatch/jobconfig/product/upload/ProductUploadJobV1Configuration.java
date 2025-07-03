package com.example.fourdollarbatch.jobconfig.product.upload;

import com.example.fourdollardomain.product.domain.Product;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ProductUploadJobV1Configuration extends DefaultBatchConfiguration {

    @Bean
    public Job productUploadJob(JobRepository jobRepository,
                                Step productUploadStep) {
        return new JobBuilder("productUploadJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(productUploadStep)
                .build();
    }

    @Bean
    @JobScope
    public Step productUploadStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  ProductUploadJobParameter jobParameter,
                                  ItemReader<ProductCsvDto> productCsvReader,
                                  ItemProcessor<ProductCsvDto, Product> productProcessor,
                                  ItemWriter<Product> productWriter) {
        return new StepBuilder("productUploadStep", jobRepository)
                .<ProductCsvDto, Product>chunk(jobParameter.getChunkSize(), transactionManager)
                .reader(productCsvReader)
                .processor(productProcessor)
                .writer(productWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ProductCsvDto> productCsvReader(ProductUploadJobParameter jobParameter) {
        return new FlatFileItemReaderBuilder<ProductCsvDto>()
                .name("productCsvReader")
                .resource(new FileSystemResource(jobParameter.getInputFilePath()))
                .delimited()
                .names(getFieldNames(ProductCsvDto.class).toArray(String[]::new))
                .linesToSkip(1)
                .targetType(ProductCsvDto.class)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<ProductCsvDto, Product> productProcessor() {
        return item -> {
            log.debug("Processing product: {}", item.title());

            return Product.builder()
                    .title(item.title())
                    .image(item.image())
                    .price(item.lprice())
                    .mallName(item.mallName())
                    .brand(item.brand())
                    .maker(item.maker())
                    .category1(item.category1())
                    .category2(item.category2())
                    .category3(item.category3())
                    .category4(item.category4())
                    .build();
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Product> productWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Product>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
    }

    @Bean
    @JobScope
    public ProductUploadJobParameter productUploadJobParameter() {
        return new ProductUploadJobParameter();
    }

    private List<String> getFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                fieldNames.add(field.getName());
            }
        }
        return fieldNames;
    }

}
