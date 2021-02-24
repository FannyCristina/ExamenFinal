/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.p2.examen.dao;

import ec.edu.ups.p2.examen.modelo.Proveedor;
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
public class ProveedorDAO {

   @PersistenceContext(name = "ExamenPersistenceUnit")
    private EntityManager em;

    public ProveedorDAO() {
    }
    
     public boolean insert(Proveedor proveedor) throws Exception {
        boolean bandera = true;
        try {
            System.out.println("si creo que llega aca");
            em.persist(proveedor);
            bandera = true;
        } catch (Exception e) {
            bandera = false;
            throw new Exception("Erro ingreso Producto " + e.getMessage());

        }

        return bandera;
    }

    public void delete(Proveedor proveedor) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(proveedor.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " + e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Producto " + e.getMessage());
        }
    }

    public void update(Proveedor proveedor) throws Exception {
        try {
            em.merge(proveedor);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Producto " + e.getMessage());
        }
    }

    public Proveedor read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Proveedor.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Producto " + e.getMessage());
        }
    }

    public List<Proveedor> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Proveedor.findAll");
            List<Proveedor> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Producto " + e.getMessage());
        }

    }

    public Proveedor findByNombre(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Proveedor.findByNombre");
            q.setParameter("nombre", Integer.parseInt(id));
            return (Proveedor) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " + e.getMessage());
        }

    }
}
