package ec.edu.espe.distribuidas.examen3batch.dao;

import ec.edu.espe.distribuidas.examen3batch.model.Transaccion;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    List<Transaccion> findByEstdo(String estado);
}
