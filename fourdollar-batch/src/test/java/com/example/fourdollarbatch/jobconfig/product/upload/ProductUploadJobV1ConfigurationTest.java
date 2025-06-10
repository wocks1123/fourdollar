package com.example.fourdollarbatch.jobconfig.product.upload;

import com.example.fourdollarbatch.FourdollarBatchApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringJUnitConfig(classes = {FourdollarBatchApplication.class})
@ActiveProfiles("test")
@Sql(scripts = {
        "/batch-ddl.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class ProductUploadJobV1ConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @DisplayName("상품을 업로드한다.")
    void test01(@Autowired Job productUploadJobV1) throws Exception {
        // given
        Resource resource = resourceLoader.getResource("classpath:data/product.csv");
        String filePath = resource.getFile().getAbsolutePath();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("inputFilePath", filePath)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncherTestUtils.setJob(productUploadJobV1);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

}
