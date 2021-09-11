/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.service;

import ec.edu.espe.distribuidas.examen3batch.dao.ConsolidadoRepository;
import ec.edu.espe.distribuidas.examen3batch.model.Consolidado;
import org.springframework.stereotype.Service;

@Service
public class ConsolidadoService {
    private final ConsolidadoRepository consolidadoRepo;

    public ConsolidadoService(ConsolidadoRepository consolidadoRepo) {
        this.consolidadoRepo = consolidadoRepo;
    }
    
    
    
    public void crearConsolidado(Consolidado consolidado){
        this.consolidadoRepo.save(consolidado);
    }
}
