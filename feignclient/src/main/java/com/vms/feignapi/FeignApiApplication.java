package com.vms.feignapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApiApplication.class, args);
	}



    @Bean
    public static JaegerTracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        Configuration config = new Configuration("Order-Consumer").withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }

}
