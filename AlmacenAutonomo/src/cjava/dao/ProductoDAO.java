package cjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cjava.database.AccesoDB;
import cjava.entity.ProductoTO;
import cjava.servicio.ICrudDao;

public class ProductoDAO implements ICrudDao<ProductoTO> {

  // variables
  Connection cn = null;
  Statement stm = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  String sql = null;
  int ok;
  List<ProductoTO> lista = null;
  ProductoTO pr = null;

  // metodos para la persistencia de datos
  @Override
  public int create(ProductoTO t) throws Exception {
    int ok = 0;
    try {
      cn = AccesoDB.getConnection();
      // genera codigo de producto
      String query = "select valor from control where parametro='Productos'";
      stm = cn.createStatement();
      rs = stm.executeQuery(query);
      rs.next();
      int cont = rs.getInt("valor");
      rs.close();
      String query1 = "update control set valor=valor +1 where parametro='Productos'";
      Statement stm1 = cn.createStatement();
      stm1.executeUpdate(query1);
      String idpro = "A00" + String.valueOf(cont);
      t.setIdproducto(idpro);
      String sql = "insert into productos(idproducto,descripcion,idcategoria,preciocompra,precioventa,stock)"
              + " values(?,?,?,?,?,?)";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, t.getIdproducto());
      pstm.setString(2, t.getDescripcion());
      pstm.setInt(3, t.getIdcategoria());
      pstm.setDouble(4, t.getPreciocompra());
      pstm.setDouble(5, t.getPrecioventa());
      pstm.setInt(6, t.getStock());
      ok = pstm.executeUpdate() == 1 ? 1 : 0;
      pstm.close();
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        //cn.close();
      } catch (Exception e) {
      }
    }
    return ok;
  }

  @Override
  public int update(ProductoTO t) throws Exception {
    int ok = 0;
    try {
      cn = AccesoDB.getConnection();
      sql = "update productos set descripcion=?,idcategoria=?,preciocompra=?,"
              + "precioventa=?,stock=? where idproducto=?";
      ps = cn.prepareStatement(sql);
      ps.setString(1, t.getDescripcion());
      ps.setInt(2, t.getIdcategoria());
      ps.setDouble(3, t.getPreciocompra());
      ps.setDouble(4, t.getPrecioventa());
      ps.setInt(5, t.getStock());
      ps.setString(6, t.getIdproducto());
      ok = ps.executeUpdate() == 1 ? 1 : 0;
      ps.close();
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        // cn.close();
      } catch (Exception e) {
      }
    }
    return ok;
  }

  @Override
  public int delete(ProductoTO t) throws Exception {
    int ok = 0;
    try {
      cn = AccesoDB.getConnection();
      sql = " delete from productos where idproducto=?";
      ps = cn.prepareStatement(sql);
      ps.setString(1, t.getIdproducto());
      ok = ps.executeUpdate() == 1 ? 1 : 0;
      ps.close();
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        //cn.close();
      } catch (Exception e) {
      }
    }
    return ok;
  }

  @Override
  public ProductoTO find(Object t) throws Exception {
    pr = null;
    try {
      cn = AccesoDB.getConnection();
      sql = "Select * from Productos where idproducto=?";
      ps = cn.prepareStatement(sql);
      ps.setString(1, t.toString());
      rs = ps.executeQuery();
      if (rs.next()) {
        pr.setIdproducto(rs.getString(1));
        pr.setDescripcion(rs.getString(2));
        pr.setIdcategoria(rs.getInt(3));
        pr.setPreciocompra(rs.getDouble(4));
        pr.setPrecioventa(rs.getDouble(5));
        pr.setStock(rs.getInt(6));
      }
      System.out.println("codigo" + rs.getString(1));
      rs.close();
      ps.close();
    } catch (Exception e) {
      throw e;
    } finally {
    }
    return pr;

  }

  @Override
  public List<ProductoTO> readAll() throws Exception {

    lista = new ArrayList<>();
    try {

      cn = AccesoDB.getConnection();

      sql = "select  * from productos order by Idproducto";
      stm = cn.createStatement();
      rs = stm.executeQuery(sql);
      while (rs.next()) {
        pr = new ProductoTO();
        pr.setIdproducto(rs.getString(1));
        pr.setDescripcion(rs.getString(2));
        pr.setIdcategoria(rs.getInt(3));
        pr.setPrecioventa(rs.getDouble(5));
        pr.setStock(rs.getInt(6));

        lista.add(pr);

      }
      rs.close();
      stm.close();

    } catch (Exception e) {
      // TODO: handle exception
      throw e;
    } finally {
      // cn.close();
    }
    return lista;
  }

  @Override
  public List<ProductoTO> readAll(Object t, int op) throws Exception {
    lista = new ArrayList<>();
    try {
      cn = AccesoDB.getConnection();
      sql = "select  * from productos";
      switch (op) {
        case 1:
          sql += " where Descripcion like ?";
          ps = cn.prepareStatement(sql);
          ps.setString(1, (String) (t + "%"));
          break;
        case 2:
          sql += " where IdCategoria=?";
          ps = cn.prepareStatement(sql);
          ps.setInt(1, (int) t);
          break;
      }
      rs = ps.executeQuery();
      while (rs.next()) {
        pr = new ProductoTO();
        pr.setIdproducto(rs.getString(1));
        pr.setDescripcion(rs.getString(2));
        pr.setIdcategoria(rs.getInt(3));
        pr.setPrecioventa(rs.getDouble(5));
        pr.setStock(rs.getInt(6));
        lista.add(pr);
      }
      rs.close();
      ps.close();
    } catch (Exception e) {
      // TODO: handle exception
      throw e;
    } finally {
      // cn.close();
    }
    return lista;
  }

  public ProductoTO Buscar(String t) throws Exception {
   ProductoTO p = null;
    try {
      cn = AccesoDB.getConnection();
      sql = "Select * from Productos where idproducto=?";
      ps = cn.prepareStatement(sql);
      ps.setString(1, t);
      rs = ps.executeQuery();
      if (rs.next()) {
        p=new ProductoTO();
        p.setIdproducto(rs.getString(1));
        p.setDescripcion(rs.getString(2));
        p.setIdcategoria(rs.getInt(3));
        p.setPreciocompra(rs.getDouble(4));
        p.setPrecioventa(rs.getDouble(5));
        p.setStock(rs.getInt(6));
      }     
      rs.close();
      ps.close();
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        // cn.close();
      } catch (Exception ex) {
        throw ex;
      }
    }
    return p;

  }

}
