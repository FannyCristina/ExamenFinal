/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.on;

import ec.edu.ups.p2.examen.dao.ProveedorDAO;
import ec.edu.ups.p2.examen.modelo.Producto;
import ec.edu.ups.p2.examen.modelo.Proveedor;
import java.util.ArrayList;
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
public class ProveedorON {

    @Inject
    ProveedorDAO proveedorDAO;

    public boolean guardarProveedor(Proveedor p) {
        try {
            String nombre ="";
            String codigo="";
            int a=0;
            Proveedor pro = new Proveedor();
            pro.setNombre(p.getNombre());
            pro.setServicio(p.getServicio());
            
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setCodigo(codigo);
            producto.setStock(a);
            producto.setProveedorId(pro);
            List<Producto> lista = new ArrayList<>();
            pro.setProductoList(lista);
            boolean respuesta = proveedorDAO.insert(pro);
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(ProveedorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
  
    public List<Proveedor> listarProveedores() {
        try {
            return proveedorDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProveedorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Proveedor buscarProducto(String nombre) {
        try {
            return proveedorDAO.findByNombre(nombre);
        } catch (Exception ex) {
            Logger.getLogger(ProveedorON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}


