/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransaccionRQ {
    private String idCajero;
    private String tipo; //RET Y DEP
    private BigDecimal montoTransaccion;
    private Integer billetes10;
    private Integer billetes20;
    
}
