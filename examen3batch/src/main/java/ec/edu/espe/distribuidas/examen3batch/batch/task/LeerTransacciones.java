
package ec.edu.espe.distribuidas.examen3batch.batch.task;

import ec.edu.espe.distribuidas.examen3batch.service.TransaccionService;
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
public class LeerTransacciones implements Tasklet, StepExecutionListener{

    private ExecutionContext executionContext;
    
    @NonNull
    private final TransaccionService service;
     
    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("exeute");
        executionContext.put("transacciones", this.service.encontrarPorEstado("PEN"));
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
