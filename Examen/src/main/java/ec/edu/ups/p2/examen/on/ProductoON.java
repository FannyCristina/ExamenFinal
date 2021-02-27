/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.on;

import ec.edu.ups.p2.examen.dao.ProductoDAO;
import ec.edu.ups.p2.examen.modelo.Producto;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public void actualizarStockProducto(Producto producto) {
        try {
            productoDAO.update(producto);
        } catch (Exception ex) {
            System.out.println("Error: ex " + ex.getMessage());
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public boolean actualizarStockReset(Producto producto, int cantidad) {
        try {
            String direcion = producto.getProveedorId().getServicio()+"?codigo="+ producto.getCodigo()+"&cantidad="+cantidad; 
            System.out.println(direcion);
            URL url = new URL(direcion);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error en el servico"+"Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                
             
                if(output.equals("Su pedido fue un exito")){
                    this.actualizarStockProducto(producto);
                    return  true;
                }else{
                    return  false;
                }
                
            }
            conn.disconnect();
            
        } catch (Exception ex) {
            System.out.println("Error: ex " + ex.getMessage());
            Logger.getLogger(ProductoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
}
