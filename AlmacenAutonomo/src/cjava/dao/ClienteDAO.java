package cjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cjava.database.AccesoDB;
import cjava.entity.ClienteTO;
import cjava.servicio.ICrudDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements ICrudDao<ClienteTO> {

    // variables
    private Connection cn = null;
    private Statement stm = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    ClienteTO p = null;
    List<ClienteTO> lista;

    @Override
    public int create(ClienteTO o) throws Exception {
        int ok;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            //generar codigo
            sql = "select valor from control where parametro='Clientes'";
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            int nro = rs.getInt(1);
            rs.close();
            sql = "update control set valor=valor + 1 where parametro='Clientes'";
            stm = cn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            String cod = "";
            if (nro < 10) {
                cod = "C000" + nro;
            } else {
                cod = "C00" + nro;
            }
            o.setIdcliente(cod);
            // insertar
            sql = "insert into "
                    + "clientes(idcliente,nombre,direccion,rucdni,telefono)"
                    + " values(?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            // preparar los valores de los parametros
            ps.setString(1, o.getIdcliente());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getDireccion());
            ps.setString(4, o.getRuc());
            ps.setString(5, o.getTelefono());
            //ejecutar comando
            ok = ps.executeUpdate() == 1 ? 1 : 0;
            ps.close();

        } catch (Exception e) {
            try {
                cn.rollback();// deshace la transaccion
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
           // cn.close();
        }
        return ok;
    }

    @Override
    public int update(ClienteTO o) throws Exception {
        int ok;
        try {
            cn = AccesoDB.getConnection();
            // generar codigo de Cliente            
            sql = "update clientes set "
                    + "nombre=?,direccion=?,rucdni=?,telefono=? where idcliente=?";
            ps = cn.prepareStatement(sql);
            // preparar los valores de los parametros 
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDireccion());
            ps.setString(3, o.getRuc());
            ps.setString(4, o.getTelefono());
            ps.setString(5, o.getIdcliente());
            //ejecutar comando
            ok = ps.executeUpdate() == 1 ? 1 : 0;
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
           // cn.close();
        }
        return ok;
    }

    @Override
    public int delete(ClienteTO o) throws Exception {
        int ok;
        try {
            cn = AccesoDB.getConnection();
            sql = "delete from clientes where idcliente=?";
            ps = cn.prepareStatement(sql);
            // preparar los valores de los parametros           
            ps.setString(1, o.getIdcliente());
            //ejecutar comando
            ok = ps.executeUpdate() == 1 ? 1 : 0;
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
           // cn.close();
        }
        return ok;
    }

    @Override
    public List<ClienteTO> readAll() throws Exception {
        lista = new ArrayList<>();
        try {
            cn = AccesoDB.getConnection();
            stm = cn.createStatement();
            rs = stm.executeQuery("select * from clientes order by idcliente");
            while (rs.next()) {
                p = new ClienteTO();
                p.setIdcliente(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setRuc(rs.getString(4));
                p.setTelefono(rs.getString(5));
                lista.add(p);
            }
            rs.close();
            stm.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
           // cn.close();
        }
        return lista;
    }

    @Override
    public ClienteTO find(Object id) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from clientes where idcliente=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, (String) id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new ClienteTO();
                p.setIdcliente(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDireccion(rs.getString(3));
                p.setRuc(rs.getString(4));
                p.setTelefono(rs.getString(5));
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                throw e;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //cn.close();
        }
        return p;
    }

    @Override
    public List<ClienteTO> readAll(Object t, int op) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
