package com.ayi.ejercicio01.controller;

import com.ayi.ejercicio01.dao.modelo.Producto;
import com.ayi.ejercicio01.service.interfaces.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductoController {

    private final IProductoService iProductoService;


    @Autowired
    public ProductoController(IProductoService iProductoService) {
        this.iProductoService = iProductoService;
    }


    //Alta Nuevo Producto (form)
    @GetMapping("/formNuevoProducto")
    public String formNuevoProducto(Model model) {
        Producto producto = new Producto();

        model.addAttribute("titulo","Alta de nuevo Producto");
        model.addAttribute("MProducto",producto);

        return "nuevoProducto";
    }

    //Guardar Nuevo Producto
    @PostMapping("/nuevoProducto")
    public String guardaProducto(@Valid @ModelAttribute("MProducto") Producto producto,
                                 BindingResult result,
                                 Model model) {
        // Si hay errores de validación, regresar al formulario
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Alta de nuevo Producto");
            return "nuevoProducto";
        }

        // Verificar si el código EAN ya existe
        if (iProductoService.existePorCodigoEan(producto.getCodigoEan())) {
            // Agregar mensaje de error
            model.addAttribute("error", "El código EAN ya está registrado.");
            // Volver a cargar el formulario con el producto actual
            model.addAttribute("titulo", "Alta de nuevo Producto");
            model.addAttribute("MProducto", producto);
            return "nuevoProducto";
        }

        // Si no hay errores, guardar el producto
        iProductoService.guardarProducto(producto);
        return "redirect:/listado";
    }


    //Eliminar Producto
    @GetMapping("/eliminarProducto/{idProducto}")
    public String eliminarProducto(@PathVariable Long idProducto){
        iProductoService.eliminarProducto(idProducto);

        return "redirect:/listado";
    }

}
