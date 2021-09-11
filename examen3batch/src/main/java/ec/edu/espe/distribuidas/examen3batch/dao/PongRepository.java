package ec.edu.espe.distribuidas.examen3batch.dao;

import ec.edu.espe.distribuidas.examen3batch.model.Pong;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PongRepository extends MongoRepository<Pong,String>{
    
}
