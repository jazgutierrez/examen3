/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.examen3web.controller;

import ec.edu.espe.distribuidas.examen3web.dto.CajeroRS;
import ec.edu.espe.distribuidas.examen3web.model.Cajero;
import ec.edu.espe.distribuidas.examen3web.service.CajeroService;
import ec.edu.espe.distribuidas.examen3web.transaform.CajeroRSTransform;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/cajero")
public class CajeroController {

    private final CajeroService service;

    public CajeroController(CajeroService service) {
        this.service = service;
    }

    @GetMapping(value = "monto/{monto}")
    public ResponseEntity obtenerCajeroMontoMenor(@PathVariable("monto") BigDecimal monto) {
        try {

            List<Cajero> cajeros = service.obtenerCajerosMontoMenor(monto);
            List<CajeroRS> cajerosRS = new ArrayList<>();
            cajeros.forEach(t -> {
                cajerosRS.add(CajeroRSTransform.buildCajeroRS(t));
            });
            log.info("Número de cajeros {} con monto menor a {} ", cajeros.size(), monto);
            return ResponseEntity.ok(cajerosRS);
        } catch (Exception e) {
            log.error("Ocurrio un error al buscar la consulta. {} - retorna badrequest", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity obtenerCajeroMontoMenor(@PathVariable("id") String id) {
        try {

            Optional<Cajero> cajero = service.obtenerPorId(id);
            if (cajero.isEmpty()) {
                throw new Exception("No existe este cajero");
            }

            CajeroRS cajeroRS = CajeroRSTransform.buildCajeroRS(cajero.get());

            log.info("El cajero {}", cajero);
            return ResponseEntity.ok(cajeroRS);
        } catch (Exception e) {
            log.error("Ocurrio un error al buscar la consulta. {} - retorna badrequest", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
