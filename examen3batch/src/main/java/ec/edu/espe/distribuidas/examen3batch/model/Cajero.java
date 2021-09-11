/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.model;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cajero")
public class Cajero {
    
    @Id
    private String id;
    private String nombre;
    private String dirección;
    private BigDecimal montoTotal;
    private Integer totalBilletes10;
    private Integer totalBilletes20;
    
}
