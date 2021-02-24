/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.proveedor2.dao;

import ec.edu.ups.p2.proveedor2.modelo.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Fanny
 */
@Stateless
public class ProductoDAO {

    @PersistenceContext(name = "Proveedor2PersistenceUnit")
    private EntityManager em;

    public ProductoDAO() {
    }

    public boolean insert(Producto producto) throws Exception {
        boolean bandera = true;
        try {
            System.out.println("Llega aca >>> ");
            em.persist(producto);
            bandera = true;
        } catch (Exception e) {
            bandera = false;
            throw new Exception("Erro ingreso Cliente " + e.getMessage());

        }
        System.out.println(bandera + " Esta es la vandera");
        return bandera;
    }

    public List<Producto> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Producto.findAll");
            List<Producto> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " + e.getMessage());
        }

    }
     public Producto findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Producto.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Producto) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " + e.getMessage());
        }

    }

    public void update(Producto producto) throws Exception {
        try {
            em.merge(producto);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " + e.getMessage());
        }
    }

}
