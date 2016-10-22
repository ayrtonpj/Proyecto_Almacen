package cjava.controller;

import java.util.List;

import cjava.dao.ProductoDAO;
import cjava.entity.ProductoTO;

public class ProductoBLL {

  // variables
    ProductoDAO dao = null;

    public ProductoBLL() {
        dao = new ProductoDAO();
    }
    // metodos de negocio

    public List<ProductoTO> ProductoListar() throws Exception {

        return dao.readAll();
    }

    public List<ProductoTO> ProductoListar(Object o, int op) throws Exception {

        return dao.readAll(o, op);
    }

    public ProductoTO ProductoBuscar(String o) throws Exception {
        return dao.Buscar(o);
    }

    public int ProductoAdicionar(ProductoTO p) throws Exception {
        return dao.create(p);
    }

    public int ProductoActualizar(ProductoTO p) throws Exception {
        return dao.update(p);
    }

    public int ProductoEliminar(ProductoTO p) throws Exception {
        return dao.delete(p);
    }

}
