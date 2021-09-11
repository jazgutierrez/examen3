/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.transaform;

import ec.edu.espe.distribuidas.examen3web.dto.CajeroRS;
import ec.edu.espe.distribuidas.examen3web.model.Cajero;

/**
 *
 * @author yazbe
 */
public class CajeroRSTransform {
    public static CajeroRS buildCajeroRS(Cajero cajero) {
        return CajeroRS.builder()
                .id(cajero.getId())
                .nombre(cajero.getNombre())
                .montoTotal(cajero.getMontoTotal())
                .totalBilletes10(cajero.getTotalBilletes10())
                .totalBilletes20(cajero.getTotalBilletes20())
                .build();
    }
}
