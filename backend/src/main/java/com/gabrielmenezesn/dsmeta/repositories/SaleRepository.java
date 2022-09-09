package com.gabrielmenezesn.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gabrielmenezesn.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  // Implementação customizada para findSales onde
  // se buscará por um 'item' da Classe sale onde
  // seu parâmetro de datas estiver dentre o mínimo
  // e o máximo estabelecido
  // -> ordenado em decrescente - maiores vendas primeiro
  @Query("SELECT item FROM Sale item WHERE item.date BETWEEN :min AND :max ORDER BY item.amount DESC")
  Page<Sale> findAll(LocalDate min, LocalDate max, Pageable pageable);
}
