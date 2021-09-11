/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3batch.dao;

import ec.edu.espe.distribuidas.examen3batch.model.Consolidado;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConsolidadoRepository extends MongoRepository<Consolidado, String> {
    
}
