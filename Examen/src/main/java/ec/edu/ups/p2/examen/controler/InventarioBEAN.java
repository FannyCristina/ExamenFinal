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
import ec.edu.ups.p2.examen.service.FerreteriaReset;
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
    private Producto produc;
    private Producto auxproducto;
    private String codigo;
    private int stock;
    private int cantida;
    private List<Producto> listaProducto;

    @Inject
    ProductoON productoON;

    @Inject
    FerreteriaReset reset;

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

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

  

    public Producto getAuxproducto() {
        return auxproducto;
    }

    public void setAuxproducto(Producto auxproducto) {
        this.auxproducto = auxproducto;
    }

    public String buscaProductoID(String codigo) {
        try {
            auxproducto = productoON.buscarProducto(codigo);
        } catch (Exception ex) {
            Logger.getLogger(InventarioBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String actualizarStock() {
        try {

            stock = auxproducto.getStock() + cantida;
            auxproducto.setStock(stock);
            productoON.actualizarStockReset(auxproducto,this.cantida);
        } catch (Exception ex) {
            Logger.getLogger(InventarioBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
