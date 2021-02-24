/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.on;

import ec.edu.ups.p2.examen.dao.ProductoDAO;
import ec.edu.ups.p2.examen.modelo.Producto;
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

    public void actProducto(Producto p) {
        try {
            p.setStock(p.getStock() - 1);
            productoDAO.update(p);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Producto> listarProductos() {
        try {
            return productoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Producto buscarProducto(String codigo) {
        try {
            return productoDAO.findByCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   public Producto buscarProductoID(String codigo) {
        try {
            return productoDAO.findByID(codigo);
        } catch (Exception ex) {
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
