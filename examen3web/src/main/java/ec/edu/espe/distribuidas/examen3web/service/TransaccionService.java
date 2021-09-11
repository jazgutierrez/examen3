/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.service;

import ec.edu.espe.distribuidas.examen3web.dao.CajeroRepository;
import ec.edu.espe.distribuidas.examen3web.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.examen3web.exception.CreateException;
import ec.edu.espe.distribuidas.examen3web.model.Cajero;
import ec.edu.espe.distribuidas.examen3web.model.Transaccion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransaccionService {

    private final TransaccionRepository transaccionRepo;
    private final CajeroRepository cajeroRepo;

    public TransaccionService(TransaccionRepository transaccionRepo, CajeroRepository cajeroRepo) {
        this.transaccionRepo = transaccionRepo;
        this.cajeroRepo = cajeroRepo;
    }

    public void crearTransaccion(Transaccion transaccion) {
        Optional<Cajero> cajero = this.cajeroRepo.findById(transaccion.getIdCajero());
        if (cajero.isEmpty()) {
            throw new CreateException("El cajero no existe");
        }

        transaccion.setEstado("PEN");
        transaccion.setFecha(LocalDateTime.now());
        log.info("Se va a crear una transaccion con la siguiente informacion: {}", transaccion);

        if ("RET".equals(transaccion.getTipo())) {
            if (cajero.get().getMontoTotal().compareTo(transaccion.getMontoTransaccion()) == 1) {
               
                BigDecimal dinero10 = new BigDecimal(transaccion.getBilletes10() * 10);
                BigDecimal dinero20 = new BigDecimal(transaccion.getBilletes20() * 20);
                BigDecimal total = dinero10.add(dinero20);
                if (total.compareTo(transaccion.getMontoTransaccion()) == 0) {
                
                    cajero.get().setMontoTotal(cajero.get().getMontoTotal().subtract(transaccion.getMontoTransaccion()));
                    cajero.get().setTotalBilletes10(cajero.get().getTotalBilletes10() - transaccion.getBilletes10());
                    cajero.get().setTotalBilletes20(cajero.get().getTotalBilletes20() - transaccion.getBilletes20());
                    if(cajero.get().getTotalBilletes10()<0 || cajero.get().getTotalBilletes20()<0 ){
                        throw new CreateException("No existen billetes sufucientes");
                    }
                    this.transaccionRepo.save(transaccion);
                    this.cajeroRepo.save(cajero.get());
                 }
            }
        }
            if ("DEP".equals(transaccion.getTipo())) {
                BigDecimal dinero10 = new BigDecimal(transaccion.getBilletes10() * 10);
                BigDecimal dinero20 = new BigDecimal(transaccion.getBilletes20() * 20);
                BigDecimal total = dinero10.add(dinero20);
                if (total.compareTo(transaccion.getMontoTransaccion()) == 0) {
                    this.transaccionRepo.save(transaccion);
                    cajero.get().setMontoTotal(cajero.get().getMontoTotal().add(transaccion.getMontoTransaccion()));
                    cajero.get().setTotalBilletes10(cajero.get().getTotalBilletes10() + transaccion.getBilletes10());
                    cajero.get().setTotalBilletes20(cajero.get().getTotalBilletes20() + transaccion.getBilletes20());
                    this.cajeroRepo.save(cajero.get());
                }

            }

        
    }
}
