package cjava.dao;

import cjava.database.AccesoDB;
import cjava.entity.CategoriaTO;
import cjava.servicio.ICrudDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICrudDao<CategoriaTO> {

  // variables
  Connection cn = null;
  Statement stm = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  String sql = null;
  int ok;
  List<CategoriaTO> lista = null;
  CategoriaTO cat = null;

  @Override
  public int create(CategoriaTO t) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int update(CategoriaTO t) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int delete(CategoriaTO t) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public CategoriaTO find(Object t) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<CategoriaTO> readAll() throws Exception {
    lista = new ArrayList<>();
    try {
      cn = AccesoDB.getConnection();
      sql = "select * from categorias order by idcategoria";
      stm = cn.createStatement();
      //ejecutar comando
      rs = stm.executeQuery(sql);
      while (rs.next()) {
        cat = new CategoriaTO();
        cat.setIdcategoria(rs.getInt("idcategoria"));
        cat.setNombre(rs.getString("nombre"));
        lista.add(cat);
      }
      rs.close();
      stm.close();
    } catch (Exception e) {
      throw e;
    } finally {
    }
    return lista;

  }

  @Override
  public List<CategoriaTO> readAll(Object t, int op) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
