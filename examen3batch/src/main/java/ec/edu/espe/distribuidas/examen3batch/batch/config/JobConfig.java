/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.batch.config;

import ec.edu.espe.distribuidas.examen3batch.batch.task.GeneradorRandomico;
import ec.edu.espe.distribuidas.examen3batch.batch.task.Imprimir;
import ec.edu.espe.distribuidas.examen3batch.service.PongService;
import javax.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAutoConfiguration
//@EnableScheduling
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Resource
    private PongService pongService;
    
    @Bean
    protected Step generarNumero(){
        return stepBuilderFactory.get("generarNumero").tasklet(new GeneradorRandomico()).build();
    }
    
    @Bean
    protected Step imprimirNumero(){
         return stepBuilderFactory.get("imprimirNumero").tasklet(new Imprimir(pongService)).build();
    }
    
    @Bean
    protected Job trabajo(){
        return jobBuilderFactory.get("trabajo").start(generarNumero()).next(imprimirNumero()).build();
    }
    
    
 /*   
    //Formato 24 hr
     @Scheduled(cron = "30 12 19 * * *")
    public void launchJob() throws Exception{
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(trabajo(), params);
    }
*/

}
