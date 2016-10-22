package cjava.controller;

import java.util.List;
import cjava.dao.ClienteDAO;
import cjava.entity.ClienteTO;


public class ClienteBLL {

    // atributo
    private ClienteDAO dao;

    //constructor
    public ClienteBLL() {
        dao = new ClienteDAO();
    }

    // metodos
   
    public int ClienteAdicionar(ClienteTO e) throws Exception
    {
        return dao.create(e);
    }
    public int ClienteActualizar(ClienteTO e) throws Exception {
        return dao.update(e);
    }

    public int ClienteEliminar(ClienteTO e) throws Exception {
        return dao.delete(e);
    }
    public List<ClienteTO> ListaClientes() throws Exception {
        return dao.readAll();
    }
    public ClienteTO BuscarCliente(Object x) throws Exception {
        return dao.find(x);
    }
}
