package com.ayi.ejercicio01.service.implement;

import com.ayi.ejercicio01.dao.repository.IUsuarioRepository;
import com.ayi.ejercicio01.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository iUsuarioRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    public boolean validarUsuario(String nombre, String passwordUsuario) {
        return iUsuarioRepository.findByNombreAndPasswordUsuario(nombre, passwordUsuario).isPresent();
    }
}
