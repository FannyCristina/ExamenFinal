/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.service;

import ec.edu.ups.p2.examen.controler.InventarioBEAN;
import ec.edu.ups.p2.examen.modelo.Producto;
import ec.edu.ups.p2.examen.on.ProductoON;
import ec.edu.ups.p2.examen.on.ProveedorON;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Fanny
 */
@Path("/ferreteria")
public class FerreteriaReset {

    @Inject
    ProveedorON proveedorON;

    @Inject
    ProductoON productoON;

    Producto producto = new Producto();

    @GET
    @Path("/stock")
    @Produces("application/json")
    public String actualizarStock(@QueryParam("codigo") String codigo, @QueryParam("cantidad") double cantidad) {
        try {
            producto = buscaProductoID(codigo);
            if (producto.equals(codigo)) {
                BigDecimal bd = new BigDecimal(producto.getStock() + cantidad);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                producto.setStock(bd.intValue());
                productoON.actualizarStockProducto(producto);
                return "Su pedido fue un exito";
            } else {
                return "No se pudo realizar su pedido";
            }
        } catch (Exception e) {
            return "No fue posible realizar su pedido" + e.getMessage();
        }

    }

    public Producto buscaProductoID(String id) {
        try {
            producto = productoON.buscarProducto(id);
        } catch (Exception ex) {
            Logger.getLogger(InventarioBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

}
