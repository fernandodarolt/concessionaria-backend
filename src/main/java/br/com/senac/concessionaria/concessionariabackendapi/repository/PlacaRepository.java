package br.com.senac.concessionaria.concessionariabackendapi.repository;

import br.com.senac.concessionaria.concessionariabackendapi.modelo.Placa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacaRepository extends JpaRepository<Placa, Long> {
}
