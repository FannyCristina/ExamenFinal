/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.proveedor1.service;

import ec.edu.ups.p2.proveedor1.controler.ProductoBEAN;
import ec.edu.ups.p2.proveedor1.modelo.Producto;
import ec.edu.ups.p2.proveedor1.on.ProductoON;
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
@Path("/proveedor1")
public class Proveedor1Reset {

    @Inject
    ProductoON productoON;

    Producto producto = new Producto();

    @GET
    @Path("/compra")
    @Produces("application/json")
    public String retiro(@QueryParam("id") String id, @QueryParam("cantidad") double cantidad) {
        try {
            producto = buscaProductoID(id);
            if (cantidad > producto.getStock()) {
                return "No disponemos de la cantidad solicitada";
            } else {
                BigDecimal bd = new BigDecimal(producto.getStock()-cantidad);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                producto.setStock(bd.intValue());
                productoON.actualizarStockProducto(producto);
                return "Su pedido fue un exito";
            }
        } catch (Exception e) {
            return "No fue posible realizar su pedido" + e.getMessage();
        }

    }

    public Producto buscaProductoID(String id) {
        try {
            producto = productoON.buscarClienteId(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductoBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
}
