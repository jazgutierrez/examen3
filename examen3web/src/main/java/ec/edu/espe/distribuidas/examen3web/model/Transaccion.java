/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transaccion")
public class Transaccion {
    @Id
    private String id;
    private String idCajero;
    private String tipo; //RET Y DEP
    private BigDecimal montoTransaccion;
    private Integer billetes10;
    private Integer billetes20;
    private LocalDateTime fecha;
    private String estado;
    
}
