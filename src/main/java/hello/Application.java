package hello;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class Application {
    @Bean
    public CommandLineRunner commandLineRunner() {
        return strings ->
                System.out.println("Executed at :" + 
                      new SimpleDateFormat().format(new Date()));
     }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}