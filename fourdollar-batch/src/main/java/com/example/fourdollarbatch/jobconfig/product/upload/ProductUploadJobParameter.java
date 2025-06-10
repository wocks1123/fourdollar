package com.example.fourdollarbatch.jobconfig.product.upload;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ProductUploadJobParameter {

    @Value("#{jobParameters[chunkSize] ?: 100}")
    private int chunkSize;

    @Value("#{jobParameters[inputFilePath]}")
    private String inputFilePath;

}
