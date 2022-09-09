package com.gabrielmenezesn.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gabrielmenezesn.dsmeta.entities.Sale;
import com.gabrielmenezesn.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

  @Autowired
  private SaleRepository repository;

  /*
   * Método do tipo List de SaleRepository
   * que retorna todas as vendas
   * 
   * public List<Sale> getSales() {
   * return repository.findAll();
   * }
   */

  // Método do tipo Page que retorna os 20
  // primeiros resultados de getSales()
  // -> prioriza a performance
  public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {

    // Capturar o dia atual da máquina do usuário
    // com o fuso-horário do sistema
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

    LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
    LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

    return repository.findAll(min, max, pageable);
  }
}
