/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.service;

import ec.edu.ups.p2.examen.modelo.Producto;
import ec.edu.ups.p2.examen.on.ProductoON;
import ec.edu.ups.p2.examen.on.ProveedorON;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    Producto producto = new Producto();
    
    
    @GET
    @Path("/compra")
    @Produces("application/json")
    public String solicitarPedido(@QueryParam("id") String id, @QueryParam("cantidad") double cantidad) {
        try {
            //producto = buscaProductoID(id);
            if (cantidad > producto.getStock()) {
                return "No disponemos de la cantidad solicitada";
            } else {
                BigDecimal bd = new BigDecimal(producto.getStock()-cantidad);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                producto.setStock(bd.intValue());
                //productoON.actualizarStockProducto(producto);
                return "Su pedido fue un exito";
            }
        } catch (Exception e) {
            return "No fue posible realizar su pedido" + e.getMessage();
        }

    }

}
