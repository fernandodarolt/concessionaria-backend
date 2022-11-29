package br.com.senac.concessionaria.concessionariabackendapi.repository;

import br.com.senac.concessionaria.concessionariabackendapi.modelo.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    @Query("SELECT a FROM modelo a WHERE a.marca.id = :idMarca")
    public List<Modelo> getModelos(@Param("idMarca") Long idMarca);

}
