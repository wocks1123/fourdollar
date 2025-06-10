package com.example.fourdollarbatch.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@Slf4j
class JobController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;


    @PostMapping("/launch/{jobName}")
    ResponseEntity<?> launchJob(@PathVariable String jobName,
                                @RequestBody Map<String, String> params) {
        Job job;
        try {
            job = jobRegistry.getJob(jobName);
        } catch (NoSuchJobException e) {
            log.error("Job not found: {}", jobName);
            return ResponseEntity.badRequest()
                    .body(new JobResponse("Job not found: " + jobName));
        }

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            jobParametersBuilder.addString(entry.getKey(), entry.getValue());
        }

        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        log.info("Launching job: {}, with params: {}", jobName, jobParameters);
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException |
                 JobRestartException |
                 JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            log.error("Failed to launch job: {}", jobName, e);
            return ResponseEntity.badRequest()
                    .body(new JobResponse("failed to launch job: " + e.getMessage()));
        }

        return ResponseEntity.ok(new JobResponse("Job launched successfully: " + jobName));
    }

    public record JobResponse(String message) {
    }

}
