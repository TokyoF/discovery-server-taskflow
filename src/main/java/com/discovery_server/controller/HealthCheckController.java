
package com.discovery_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthCheckController {

  /**
   * Endpoint para verificar el estado de salud del servidor Eureka.
   *
   * @return Un mensaje indicando que el servidor está en funcionamiento.
   */
  @GetMapping
  public String healthCheck() {
    return "Eureka Server is running!";
  }
}
