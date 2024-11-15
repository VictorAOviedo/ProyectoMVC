package com.ayi.ejercicio01.controller;

import com.ayi.ejercicio01.dao.modelo.Servicio;
import com.ayi.ejercicio01.service.interfaces.IServicioService;
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
public class ServicioController {

    private final IServicioService iServicioService;

    @Autowired
    public ServicioController(IServicioService iServicioService) {
        this.iServicioService = iServicioService;
    }


    //Alta Nuevo Servicio (form)
    @GetMapping("/formNuevoServicio")
    public String formNuevoServicio(Model model) {
        Servicio servicio = new Servicio();

        model.addAttribute("titulo","Alta de nuevo Servicio");
        model.addAttribute("MServicio",servicio);

        return "nuevoServicio";
    }

    //Guardar Nuevo Servicio
    @PostMapping("/nuevoServicio")
    public String guardaServicio(@Valid @ModelAttribute("MServicio") Servicio servicio,
                                 BindingResult result,
                                 Model model){
        // Si hay errores de validación, regresar al formulario
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Alta de nuevo Servicio");
            return "nuevoServicio";
        }

        // Verificar si el código EAN ya existe
        if (iServicioService.existePorCodigoEan(servicio.getCodigoEan())) {
            // Agregar mensaje de error
            model.addAttribute("error", "El código EAN ya está registrado.");
            // Volver a cargar el formulario con el producto actual
            model.addAttribute("titulo", "Alta de nuevo Servicio");
            model.addAttribute("MServicio", servicio);
            return "nuevoServicio";
        }

        // Si no hay errores, guarda
        iServicioService.guardarServicio(servicio);
        return "redirect:/listado";
    }

    //Eliminar Servicio
    @GetMapping("/eliminarServicio/{idServicio}")
    public String eliminarServicio(@PathVariable Long idServicio){
        iServicioService.eliminarServicio(idServicio);

        return "redirect:/listado";
    }

}
