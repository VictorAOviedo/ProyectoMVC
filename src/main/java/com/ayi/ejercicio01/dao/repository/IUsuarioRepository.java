package com.ayi.ejercicio01.dao.repository;

import com.ayi.ejercicio01.dao.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreAndPasswordUsuario(String nombre, String passwordUsuario);
}
