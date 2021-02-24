/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.proveedor2.controler;

import ec.edu.ups.p2.proveedor2.modelo.Producto;
import ec.edu.ups.p2.proveedor2.on.ProductoON;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Fanny
 */

@ManagedBean
@ViewScoped
public class ProductoBEAN {    
    
    @Inject
    ProductoON productoON;

    private List<Producto> listaProductos;
    private Producto producto;

    @PostConstruct
    public void init() {
        producto = new Producto();
        listaProductos = productoON.listaProductos();

    }

    public String guardarProducto() {
        try {
            productoON.guardarProducto(producto);
            init();
        } catch (Exception ex) {
            Logger.getLogger(ProductoBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
