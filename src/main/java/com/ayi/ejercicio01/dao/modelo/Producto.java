package com.ayi.ejercicio01.dao.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    //JavaBeans
    private static final long SerialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotNull(message = "El código EAN es obligatorio")
    @Min(value = 10000000, message = "El código EAN debe tener al menos 8 dígitos")
    @Max(value = 9999999999999L, message = "El código EAN no debe exceder 13 dígitos")
    @Column(name = "codigo_ean")
    private Long codigoEan;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía.")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0.")
    @Digits(integer = 10, fraction = 2, message = "El precio debe ser un número válido con hasta 10 dígitos enteros y 2 decimales.")
    private BigDecimal precio;

    @NotNull(message = "El stock no puede ser nulo.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;
}
