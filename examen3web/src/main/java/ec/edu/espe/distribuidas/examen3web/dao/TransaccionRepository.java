package ec.edu.espe.distribuidas.examen3web.dao;

import ec.edu.espe.distribuidas.examen3web.model.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    
}
