/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.proveedor1.on;

import ec.edu.ups.p2.proveedor1.dao.ProductoDAO;
import ec.edu.ups.p2.proveedor1.modelo.Producto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fanny
 */
@Stateless
public class ProductoON {

    @Inject
    ProductoDAO productoDAO;

    public ProductoON() {
    }

    public boolean guardarProducto(Producto p) {
        try {
            Producto pro = new Producto();
            pro.setNombre(p.getNombre());
            pro.setCodigo(p.getCodigo());
            pro.setStock(p.getStock());
            boolean respuesta = productoDAO.insert(pro);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Producto> listaProductos() {
        try {
            return productoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Producto buscarClienteId(String codigp) {
        try {
            return productoDAO.findByID(codigp);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

    public void actualizarStockProducto(Producto producto) {
        try {
            productoDAO.update(producto);
        } catch (Exception ex) {
            System.out.println("Error: ex " + ex.getMessage());
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
