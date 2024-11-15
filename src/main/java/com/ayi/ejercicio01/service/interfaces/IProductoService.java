package com.ayi.ejercicio01.service.interfaces;

import com.ayi.ejercicio01.dao.modelo.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto>ObtenerProductos();
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Long id_producto);
    boolean existePorCodigoEan(Long codigoEan);

}
