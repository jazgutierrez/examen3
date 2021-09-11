package ec.edu.espe.distribuidas.examen3batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Examen3batchApplication {

    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    @Qualifier("trabajo")
    Job jobs;

    public static void main(String[] args) {
        SpringApplication.run(Examen3batchApplication.class, args);
    }

}
