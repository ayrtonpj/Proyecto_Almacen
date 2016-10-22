package cjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cjava.database.AccesoDB;
import cjava.entity.DetalleTO;
import cjava.entity.VentaTO;
import cjava.servicio.IDaoVenta;

public class VentaDAO implements IDaoVenta<VentaTO> {

  // variables

  Connection cn = null;
  Statement stm = null, stm1;
  ResultSet rs = null;
  PreparedStatement ps,psb,psa;

  String consulta;
  String query;

  @Override
  public int registraVenta(VentaTO reg) throws Exception {
    int nroVenta;
    try {
      cn = AccesoDB.getConnection();
      // inicia transaccion
      cn.setAutoCommit(false);
      // codigo genera nro de venta
      consulta = "Select Valor from Control where parametro='Ventas'";
      stm1 = cn.createStatement();
      rs = stm1.executeQuery(consulta);
      rs.next();
      nroVenta = rs.getInt("Valor");
      rs.close();
      consulta = "update Control set Valor=Valor+1 where parametro='Ventas'";
      stm1 = cn.createStatement();
      stm1.executeUpdate(consulta);
      // registra ventas
      query = "Insert Into Ventas(IdVenta,IdCliente,idEmpleado,tipodoc,Nrodoc,Fecha,Total)"
              + " Values(?,?,?,?,?,CURDATE(),?)";
      ps = cn.prepareStatement(query);
      // preparar valores a pasar al comando
      ps.setInt(1, nroVenta);
      ps.setString(2, reg.getIdcliente());
      ps.setString(3, reg.getIdempleado());
      ps.setString(4, reg.getTipodoc());
      ps.setString(5, reg.getNrodoc());
      ps.setDouble(6, reg.getTotal());
      ps.executeUpdate();// ejecuta insert de venta
      //graba detalle venta
      query = "Insert Into DetalleVenta(IdVenta,idproducto,precioventa,cantidad,importe)"
              + " Values(?,?,?,?,?)";
      psa = cn.prepareStatement(query);
      //actualiza stock
      query = "Update Productos  set Stock = Stock - ? where idProducto=?";
      psb = cn.prepareStatement(query);
      for (DetalleTO item : reg.getDetalle()) {
        // prepara valores a pasar al comando insertar detallle
        psa.setInt(1, nroVenta);
        psa.setString(2, item.getIdproducto());
        psa.setDouble(3, item.getPrecio());
        psa.setInt(4, item.getCantidad());
        psa.setDouble(5, item.getTotal());
        psa.executeUpdate();//ejecuta insert de detalle
        // actualizar stock de producto
        psb.setInt(1, item.getCantidad());
        psb.setString(2, item.getIdproducto());
        psb.executeUpdate();//ejecuta update de producto
      }
      stm1.close();
      ps.close();
      psa.close();
      psb.close();
      cn.commit(); // confirma la transaccion
    } catch (ClassNotFoundException | SQLException e2) {
      try {
        cn.rollback();// deshacer la transaccion
      } catch (SQLException e1) {
      }
      throw e2;
    } finally {
    }
    return nroVenta;
  }
}
