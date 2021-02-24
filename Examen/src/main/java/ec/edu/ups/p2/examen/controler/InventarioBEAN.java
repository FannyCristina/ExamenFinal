/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.controler;

import ec.edu.ups.p2.examen.modelo.Producto;
import ec.edu.ups.p2.examen.modelo.Proveedor;
import ec.edu.ups.p2.examen.on.ProductoON;
import ec.edu.ups.p2.examen.on.ProveedorON;
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
public class InventarioBEAN {

    private String nombre;
    private String producto;
    private String codigo;
    private String stock;
    private String cantida;
    private List<Producto> listaProducto;
    
    @Inject
    ProductoON productoON;

    @PostConstruct
    private void init() {
        listaProducto = productoON.listarProductos();
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String solicitarPedido() {

        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantida() {
        return cantida;
    }

    public void setCantida(String cantida) {
        this.cantida = cantida;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    
    
   

}
