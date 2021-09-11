/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.batch.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class GeneradorRandomico implements Tasklet, StepExecutionListener{

    private ExecutionContext executionContext;
    
    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("exeute");
        executionContext.put("numero", new Integer(7));
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution se) {
        log.info("Empezando..");
        
        executionContext=se.getJobExecution().getExecutionContext();
        
    }

    @Override
    public ExitStatus afterStep(StepExecution se) {
        log.info("final");
        return ExitStatus.COMPLETED;
    }
    
}
