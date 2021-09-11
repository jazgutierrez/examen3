/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.batch.task;

import ec.edu.espe.distribuidas.examen3batch.service.PongService;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

@Data
@Slf4j
public class Imprimir implements Tasklet, StepExecutionListener {

    private ExecutionContext executionContext;
    private Integer numero;
    @NonNull
    private final PongService service;


    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("numero: {}", numero);
        for (int i = 0; i < numero; i++) {
            service.pingPong("ping");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution se) {

        executionContext = se.getJobExecution().getExecutionContext();
        numero = (Integer) executionContext.get("numero");
    }

    @Override
    public ExitStatus afterStep(StepExecution se) {
        return ExitStatus.COMPLETED;
    }

}
