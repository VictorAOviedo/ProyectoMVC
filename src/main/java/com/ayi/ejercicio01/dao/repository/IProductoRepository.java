package com.ayi.ejercicio01.dao.repository;

import com.ayi.ejercicio01.dao.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar producto por codigo EAN
    Optional<Producto> findByCodigoEan(Long codigo_ean);

    //Verifica si ya existe codigo EAN
    boolean existsByCodigoEan(Long codigoEan);

}
