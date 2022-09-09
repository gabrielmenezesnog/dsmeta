package com.gabrielmenezesn.dsmeta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielmenezesn.dsmeta.entities.Sale;
import com.gabrielmenezesn.dsmeta.services.SaleService;
import com.gabrielmenezesn.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

  // Chamando uma variável de SaleService
  @Autowired
  private SaleService service;

  // Chamando uma variável de SmsService
  @Autowired
  private SmsService smsService;

  /*
   * Método que retorna todos os resultados
   * da consulta em forma de lista
   * 
   * @GetMapping
   * public List<Sale> getSales() {
   * return service.getSales();
   * }
   */

  // Método do tipo Page em SaleService que retorna os 20
  // primeiros resultados de getSales()
  // -> prioriza a performance
  @GetMapping
  public Page<Sale> findSales(
      // minDate e maxDate para buscas
      // em um intervalo de tempo
      @RequestParam(value = "minDate", defaultValue = "") String minDate,
      @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
      Pageable pageable) {
    return service.findSales(minDate, maxDate, pageable);
  }

  // Rota /sales/{id}/notification para
  // enviar sms
  @GetMapping("/{id}/notification")
  public void SmsNotify(@PathVariable Long id) {
    smsService.sendSms(id);
  }
}
