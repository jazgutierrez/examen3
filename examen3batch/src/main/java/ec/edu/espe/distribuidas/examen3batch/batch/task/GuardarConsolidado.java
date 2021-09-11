/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.batch.task;

import ec.edu.espe.distribuidas.examen3batch.model.Consolidado;
import ec.edu.espe.distribuidas.examen3batch.model.Transaccion;
import ec.edu.espe.distribuidas.examen3batch.service.ConsolidadoService;
import ec.edu.espe.distribuidas.examen3batch.service.TransaccionService;
import java.math.BigDecimal;
import java.util.List;
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
public class GuardarConsolidado implements Tasklet, StepExecutionListener {

    private ExecutionContext executionContext;
    private List<Transaccion> transacciones;
    @NonNull
    private final ConsolidadoService service;
    @NonNull
    private final TransaccionService transaccionService;

    public GuardarConsolidado(ConsolidadoService service, TransaccionService transaccionService) {
        this.service = service;
        this.transaccionService = transaccionService;
    }


    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("transacciones: {}", transacciones);
        Consolidado consolidado= new Consolidado();
        Integer billetes10=0;
        Integer billetes20=0;
        
        BigDecimal total=BigDecimal.ZERO;
        for(Transaccion t: transacciones){
        billetes10=t.getBilletes10()+billetes10;
        billetes20=t.getBilletes20()+billetes20;
        
        }
          consolidado.setBillete10(billetes10);
          consolidado.setBillete20(billetes20);
          consolidado.setMonto(total);
        this.service.crearConsolidado(consolidado);
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution se) {

        executionContext = se.getJobExecution().getExecutionContext();
        numero = (Integer) executionContext.get("transacciones");
    }

    @Override
    public ExitStatus afterStep(StepExecution se) {
        return ExitStatus.COMPLETED;
    }

}
