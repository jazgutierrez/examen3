/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.model;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "consolidado")
public class Consolidado {

    @Id
    private String id;
    private String idCajero;
    private BigDecimal monto;
    private Integer billete10;
    private Integer billete20;
    
    
}
