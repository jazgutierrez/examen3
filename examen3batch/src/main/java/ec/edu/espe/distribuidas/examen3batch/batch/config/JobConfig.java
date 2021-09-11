/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.batch.config;

import ec.edu.espe.distribuidas.examen3batch.batch.task.LeerTransacciones;
import ec.edu.espe.distribuidas.examen3batch.batch.task.GuardarConsolidado;
import ec.edu.espe.distribuidas.examen3batch.service.ConsolidadoService;
import ec.edu.espe.distribuidas.examen3batch.service.TransaccionService;
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
@EnableScheduling
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    JobLauncher jobLauncher;
    
    @Resource
    private TransaccionService transaccionService;
    @Resource
    private ConsolidadoService consolidadoService;
    
    @Bean
    protected Step LeerTransacciones(){
        return stepBuilderFactory.get("LeerTransacciones").tasklet(new LeerTransacciones(transaccionService)).build();
    }
    
    @Bean
    protected Step imprimirNumero(){
         return stepBuilderFactory.get("imprimirNumero").tasklet(new GuardarConsolidado(consolidadoService,transaccionService)).build();
    }
    
    @Bean
    protected Job trabajo(){
        return jobBuilderFactory.get("trabajo").start(LeerTransacciones()).next(imprimirNumero()).build();
    }
    
    
  
    //Formato 24 hr
    @Scheduled(cron = "0 15 * * * *")
    public void launchJob() throws Exception{
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(trabajo(), params);
    }


}
