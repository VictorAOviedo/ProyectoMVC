package com.ayi.ejercicio01.service.implement;

import com.ayi.ejercicio01.dao.modelo.Servicio;
import com.ayi.ejercicio01.dao.repository.IServicioRepository;
import com.ayi.ejercicio01.service.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements IServicioService {

    private final IServicioRepository iServicioRepository;

    @Autowired
    public ServicioServiceImpl(IServicioRepository iServicioRepository) {
        this.iServicioRepository = iServicioRepository;
    }

    @Override
    public List<Servicio> ObtenerServicios() {
        return (List<Servicio>) iServicioRepository.findAll() ;
    }

    @Override
    public boolean existePorCodigoEan(Long codigoEan) {
        return iServicioRepository.existsByCodigoEan(codigoEan);
    };

    @Override
    public Servicio guardarServicio(Servicio servicio) {
        // Verificar si el código EAN ya existe
        if (iServicioRepository.findByCodigoEan(servicio.getCodigoEan()).isPresent()) {
            throw new IllegalArgumentException("El código EAN ya existe.");
        }

        return iServicioRepository.save(servicio);
    }

    @Override
    public void eliminarServicio(Long id_servicio) {
        iServicioRepository.deleteById(id_servicio);
    }
}
