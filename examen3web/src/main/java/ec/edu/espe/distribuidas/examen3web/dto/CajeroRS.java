/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CajeroRS {

    private String id;
    private String nombre;
    private BigDecimal montoTotal;
    private Integer totalBilletes10;
    private Integer totalBilletes20;
}
