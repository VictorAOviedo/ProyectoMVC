package com.ayi.ejercicio01.controller;

import com.ayi.ejercicio01.service.interfaces.IProductoService;
import com.ayi.ejercicio01.service.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListadoController {

    private final IProductoService iProductoService;
    private final IServicioService iServicioService;
    private final Environment env;

    @Autowired
    public ListadoController(IProductoService iProductoService, IServicioService iServicioService, Environment env) {
        this.iProductoService = iProductoService;
        this.iServicioService = iServicioService;
        this.env = env;
    }

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        // Carga de Productos y Servicios
        var productos = iProductoService.ObtenerProductos();
        var servicios = iServicioService.ObtenerServicios();

        model.addAttribute("MProductos", productos);
        model.addAttribute("MServicios", servicios);


        // Carga los títulos desde el archivo application.properties
        String tituloProductos = env.getProperty("titulo.productos");
        String tituloServicios = env.getProperty("titulo.servicios");

        model.addAttribute("tituloProductos", tituloProductos);
        model.addAttribute("tituloServicios", tituloServicios);


        return "listado";
    }
}
