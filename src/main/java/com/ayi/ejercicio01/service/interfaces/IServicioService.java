package com.ayi.ejercicio01.service.interfaces;

import com.ayi.ejercicio01.dao.modelo.Servicio;

import java.util.List;

public interface IServicioService {
    List<Servicio> ObtenerServicios();
    Servicio guardarServicio(Servicio servicio);
    void eliminarServicio(Long id_servicio);
    boolean existePorCodigoEan(Long codigoEan);
}
