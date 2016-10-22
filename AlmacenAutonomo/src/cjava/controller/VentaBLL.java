package cjava.controller;

import cjava.dao.VentaDAO;
import cjava.entity.VentaTO;

public class VentaBLL {

    VentaDAO dao;

    public VentaBLL() {
        dao = new VentaDAO();
    }

    public int registraFacturaVenta(VentaTO v) throws Exception {
        return dao.registraVenta(v);
    }

}
