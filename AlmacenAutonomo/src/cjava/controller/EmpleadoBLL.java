package cjava.controller;

import java.util.List;
import cjava.entity.EmpleadoTO;
import cjava.dao.EmpleadoDAO;

public class EmpleadoBLL {

    EmpleadoDAO dao;

    public EmpleadoBLL() {
        dao = new EmpleadoDAO();
    }

    // metodos de negocio
    public List<EmpleadoTO> EmpleadoListar() throws Exception {
        return dao.readAll();
    }

    public EmpleadoTO EmpleadoBuscar(Object x) throws Exception {
        return dao.find(x);
    }

    public int EmpleadoAdicionar(EmpleadoTO x) throws Exception {
        return dao.create(x);
    }

    public int EmpleadoActualizar(EmpleadoTO x) throws Exception {
        return dao.update(x);
    }

    public int EmpleadoEliminar(EmpleadoTO x) throws Exception {
        return dao.delete(x);
    }

    public boolean EmpleadoValidar(String usu, String pas) throws Exception {
        return dao.validar(usu, pas);
    }
}
