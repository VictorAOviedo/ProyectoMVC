package com.ayi.ejercicio01.dao.repository;

import com.ayi.ejercicio01.dao.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio, Long> {
    // Buscar por codigo EAN
    Optional<Servicio> findByCodigoEan(Long codigo_ean);

    //Verifica si ya existe codigo EAN
    boolean existsByCodigoEan(Long codigoEan);
}
