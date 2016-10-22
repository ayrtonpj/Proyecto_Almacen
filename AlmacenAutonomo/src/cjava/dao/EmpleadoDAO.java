package cjava.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cjava.database.AccesoDB;
import cjava.entity.EmpleadoTO;
import cjava.servicio.ICrudDao;

public class EmpleadoDAO implements ICrudDao<EmpleadoTO> {

  // variables JDBC
  Connection cn = null;
  ResultSet rs = null;
  Statement stm = null;
  PreparedStatement ps = null;
  CallableStatement cs = null;
  String sql = null;
  List<EmpleadoTO> lista = null;
  EmpleadoTO p;
  final String SQL_ADICIONAR = "INSERT INTO empleados (ID,Nombre,Apellidos,Email,usuario,clave) VALUES(?,?,?,?,?,?)";
  final String SQL_ACTUALIZAR = "{call sp_Empleado_Actualizar(?,?,?,?,?,?)}";
  final String SQL_ELIMINAR = "{call sp_Empleado_Eliminar(?)}";
  final String SQL_SELECT = "{call sp_Empleado_Listar}";

  @Override
  public int create(EmpleadoTO o) throws Exception {
    int ok;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      String cod = generaCodigo();
      o.setIdempleado(cod);
      cs = cn.prepareCall(SQL_ADICIONAR);
      // preparar los valores que le pasamos al sp
      cs.setString(1, o.getIdempleado());
      cs.setString(2, o.getApellido());
      cs.setString(3, o.getNombre());
      cs.setString(4, o.getEmail());
      cs.setString(5, o.getUsuario());
      cs.setString(6, o.getClave());
      // ejecutar sp
      ok = cs.executeUpdate() == 1 ? 1 : 0;
      cs.close();
      cn.commit();// confirma que transaccion se realizado con exito
    } catch (ClassNotFoundException | SQLException e) {
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
  public int update(EmpleadoTO o) throws Exception {
    int ok;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      cs = cn.prepareCall(SQL_ACTUALIZAR);
      cs.setString(1, o.getIdempleado());
      cs.setString(2, o.getApellido());
      cs.setString(3, o.getNombre());
      cs.setString(4, o.getEmail());
      cs.setString(5, o.getUsuario());
      cs.setString(6, o.getClave());
      ok = cs.executeUpdate() == 1 ? 1 : 0;
      cs.close();
      cn.commit();// confirma que transaccion se realizado con exito
    } catch (ClassNotFoundException | SQLException e) {
      try {
        cn.rollback();
      } catch (SQLException e1) {
      }
      throw e;
    } finally {
      //cn.close();
    }
    return ok;
  }

  @Override
  public int delete(EmpleadoTO o) throws Exception {
    int ok;
    try {
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      cs = cn.prepareCall(SQL_ELIMINAR);
      cs.setString(1, o.getIdempleado());
      ok = cs.executeUpdate() == 1 ? 1 : 0;
      cs.close();
      cn.commit();// confirma que transaccion se realizado con exito
    } catch (ClassNotFoundException | SQLException e) {
      try {
        cn.rollback();
      } catch (SQLException e1) {
      }
      throw e;
    } finally {
      // cn.close();
    }
    return ok;
  }

  @Override
  public List<EmpleadoTO> readAll() throws Exception {
    lista = new ArrayList<>();
    try {
      cn = AccesoDB.getConnection();
      stm = cn.createStatement();
      rs = stm.executeQuery("select * from empleados");
      while (rs.next()) {
        p = new EmpleadoTO();
        p.setIdempleado(rs.getString(1));
        p.setNombre(rs.getString(2));
        p.setApellido(rs.getString(3));
        p.setEmail(rs.getString(4));
        lista.add(p);
      }
      rs.close();
      stm.close();
    } catch (SQLException | ClassNotFoundException e) {
      throw e;
    } finally {
      // cn.close();
    }
    return lista;
  }

  @Override
  public EmpleadoTO find(Object o) throws Exception {
    p = null;
    try {
      cn = AccesoDB.getConnection();
      ps = cn.prepareStatement("select * from empleados where idempleado=?");
      ps.setString(1, (String) o);
      rs = ps.executeQuery();
      if (rs.next()) {
        p = new EmpleadoTO();
        p.setIdempleado(rs.getString(1));
        p.setNombre(rs.getString(2));
        p.setApellido(rs.getString(3));
        p.setEmail(rs.getString(4));
        p.setUsuario(rs.getString(5));
        p.setClave(rs.getString(6));
      }
      rs.close();
      ps.close();
    } catch (SQLException | ClassNotFoundException e) {
      throw e;
    } finally {
      // cn.close();
    }
    return p;
  }

  private String generaCodigo() throws SQLException {
    sql = "select valor from control where parametro='Empleados'";
    stm = cn.createStatement();
    rs = stm.executeQuery(sql);
    rs.next();
    int cont = rs.getInt(1);
    rs.close();
    sql = "update control set valor=valor+1 where parametro='Empleados'";
    stm = cn.createStatement();
    stm.executeUpdate(sql);
    stm.close();
    String xcod = "";
    if (cont < 10) {
      xcod = "E000" + cont;
    } else {
      xcod = "E00" + cont;
    }
    return xcod;
  }

  public boolean validar(String xuser, String xpass) throws Exception {
    boolean sw;
    try {
      cn = AccesoDB.getConnection();
      sql = "select count(*) from empleados where usuario=? and clave=?";
      ps = cn.prepareStatement(sql);
      ps.setString(1, xuser);
      ps.setString(2, xpass);
      rs = ps.executeQuery();
      rs.next();
      int x = rs.getInt(1);
      sw = x > 0;
      rs.close();
      ps.close();
    } catch (ClassNotFoundException | SQLException e) {
      throw e;
    } finally {
    }
    return sw;
  }

  @Override
  public List<EmpleadoTO> readAll(Object t, int op) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
