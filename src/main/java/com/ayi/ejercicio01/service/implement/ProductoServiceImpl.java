package com.ayi.ejercicio01.service.implement;

import com.ayi.ejercicio01.dao.modelo.Producto;
import com.ayi.ejercicio01.dao.repository.IProductoRepository;
import com.ayi.ejercicio01.service.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository iProductoRepository;

    @Autowired
    public ProductoServiceImpl(IProductoRepository iProductoRepository) {
        this.iProductoRepository = iProductoRepository;
    }


    @Override
    public List<Producto> ObtenerProductos(){
        return (List<Producto>) iProductoRepository.findAll();
    };

    @Override
    public boolean existePorCodigoEan(Long codigoEan) {
        return iProductoRepository.existsByCodigoEan(codigoEan);
    };


    @Override
    public Producto guardarProducto(Producto producto) {
        // Verificar si el código EAN ya existe
        if (iProductoRepository.findByCodigoEan(producto.getCodigoEan()).isPresent()) {
            throw new IllegalArgumentException("El código EAN ya existe.");
        }
        return iProductoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id_producto){
        iProductoRepository.deleteById(id_producto);
    };
}
