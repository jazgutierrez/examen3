package ec.edu.espe.distribuidas.examen3batch.service;

import ec.edu.espe.distribuidas.examen3batch.dao.PongRepository;
import ec.edu.espe.distribuidas.examen3batch.model.Pong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PongService {
    
    private final PongRepository pongRepository;
    
    public Pong pingPong(String ping){
        Pong pong = Pong.builder()
            .message(ping)                
            .response("pong")
            .build();
        pongRepository.save(pong);
        return pong;
    }
}
