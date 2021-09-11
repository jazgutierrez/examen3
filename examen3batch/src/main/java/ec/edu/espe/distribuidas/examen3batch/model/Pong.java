package ec.edu.espe.distribuidas.examen3batch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "pong")
public class Pong {
    @Id
    private String id;
    private String message;
    private String response;
}
