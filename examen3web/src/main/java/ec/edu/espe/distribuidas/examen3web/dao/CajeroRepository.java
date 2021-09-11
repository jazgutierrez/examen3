/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.dao;

import ec.edu.espe.distribuidas.examen3web.model.Cajero;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CajeroRepository extends MongoRepository<Cajero, String> {

    List<Cajero> findByMontoTotalLessThan(BigDecimal monto);
    
}
