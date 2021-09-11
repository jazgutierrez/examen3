/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.service;

import ec.edu.espe.distribuidas.examen3web.dao.CajeroRepository;
import ec.edu.espe.distribuidas.examen3web.model.Cajero;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CajeroService {
    private final CajeroRepository cajeroRepo;

    public CajeroService(CajeroRepository cajeroRepo) {
        this.cajeroRepo = cajeroRepo;
    }
    
    
    public List<Cajero> obtenerCajerosMontoMenor(BigDecimal monto){
        return this.cajeroRepo.findByMontoTotalLessThan(monto);
    }
    
    public Optional<Cajero> obtenerPorId(String id){
        return this.cajeroRepo.findById(id);
    }
}
