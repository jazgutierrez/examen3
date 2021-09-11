/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.service;

import ec.edu.espe.distribuidas.examen3batch.dao.TransaccionRepository;
import ec.edu.espe.distribuidas.examen3batch.model.Transaccion;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepo;

    public TransaccionService(TransaccionRepository transaccionRepo) {
        this.transaccionRepo = transaccionRepo;
    }

    public List<Transaccion> encontrarPorEstado(String estado) {
        return this.transaccionRepo.findByEstdo(estado);
    }
    public void crearTransaccion(Transaccion t){
        this.transaccionRepo.save(t);
    }
}
