/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.controller;

import ec.edu.espe.distribuidas.examen3web.dto.TransaccionRQ;
import ec.edu.espe.distribuidas.examen3web.model.Transaccion;
import ec.edu.espe.distribuidas.examen3web.service.TransaccionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {
    
    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity crearConsulta(@RequestBody TransaccionRQ transaccionRQ) {
        try {
            log.info("Se va a crear una transaccion con la siguiente informacion: {}", transaccionRQ);
            Transaccion transaccion = new Transaccion();
            transaccion.setIdCajero(transaccionRQ.getIdCajero());
            transaccion.setTipo(transaccionRQ.getTipo());
            transaccion.setBilletes10(transaccionRQ.getBilletes10());
            transaccion.setBilletes20(transaccionRQ.getBilletes20());
            transaccion.setMontoTransaccion(transaccionRQ.getMontoTransaccion());
             log.info("Se va a crear una transaccion con la siguiente informacion: {}", transaccionRQ);
            this.service.crearTransaccion(transaccion);
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error al crear la consulta. {} - retorna badrequest", e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }
    
}
