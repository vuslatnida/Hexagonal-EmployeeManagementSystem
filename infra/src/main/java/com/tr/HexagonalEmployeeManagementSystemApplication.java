package com.tr;

import com.tr.common.annotation.UseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {UseCase.class})})
public class HexagonalEmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalEmployeeManagementSystemApplication.class, args);
    }

}
