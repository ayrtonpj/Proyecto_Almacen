package cjava.view;

import cjava.controller.CategoriaBLL;
import cjava.controller.ProductoBLL;
import cjava.entity.CategoriaTO;
import cjava.entity.ProductoTO;
import java.util.List;
import javax.swing.table.DefaultTableModel;



public class productoxlineaView extends javax.swing.JInternalFrame {

    // instanciar objeto
    public productoxlineaView() throws Exception {
        initComponents();
       cargaCategorias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    tblProducto = new javax.swing.JTable();
    btnCerrar = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    cboCategoria = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    lblTotal = new javax.swing.JLabel();

    setTitle("CONSULTA DE PRODUCTOS POR LINEA");

    tblProducto.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Codigo", "Nombre", "Categoria", "Precio", "Stock"
      }
    ));
    jScrollPane1.setViewportView(tblProducto);
    if (tblProducto.getColumnModel().getColumnCount() > 0) {
      tblProducto.getColumnModel().getColumn(0).setPreferredWidth(50);
      tblProducto.getColumnModel().getColumn(1).setPreferredWidth(200);
      tblProducto.getColumnModel().getColumn(2).setPreferredWidth(60);
      tblProducto.getColumnModel().getColumn(3).setPreferredWidth(50);
      tblProducto.getColumnModel().getColumn(4).setPreferredWidth(50);
    }

    btnCerrar.setText("Cerrar");
    btnCerrar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnCerrarActionPerformed(evt);
      }
    });

    jLabel1.setText("Categoria de Producto");

    cboCategoria.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cboCategoriaActionPerformed(evt);
      }
    });

    jLabel2.setText("Total de Productos");

    lblTotal.setText("0");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(9, 9, 9))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(25, 25, 25)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel2)
            .addComponent(lblTotal))
          .addComponent(btnCerrar))
        .addContainerGap(28, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

 private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
     this.dispose();
 }//GEN-LAST:event_btnCerrarActionPerformed

 private void cboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriaActionPerformed
    listaProductos();
 }//GEN-LAST:event_cboCategoriaActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnCerrar;
  private javax.swing.JComboBox cboCategoria;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblTotal;
  private javax.swing.JTable tblProducto;
  // End of variables declaration//GEN-END:variables
   // instanciar objeto
  CategoriaBLL ocat=new CategoriaBLL();
  ProductoBLL obj=new ProductoBLL();
  
  private void cargaCategorias() {
    try {
      cboCategoria.removeAllItems();
      for (CategoriaTO c : ocat.ListaCategorias()) {
         cboCategoria.addItem(c);
      }
    } catch (Exception e) {
    }
  }

  private void listaProductos() {
    try {
        int id=cboCategoria.getSelectedIndex()+1;
       List<ProductoTO> lista = obj.ProductoListar(id, 2);//filtra por idcategoria
      int nfilas = 0;
      if (lista != null && !lista.isEmpty()) {
        nfilas = lista.size();
        lblTotal.setText(nfilas + "");
        verProducto(lista);
      }
    } catch (Exception e) {
    }
  }

  private void verProducto(List<ProductoTO> lista) {
      //variable de tipo table model
    DefaultTableModel tabla = (DefaultTableModel) tblProducto.getModel();
    tabla.setRowCount(0);
    for (ProductoTO p : lista) {
      Object[] datos = {p.getIdproducto(), p.getDescripcion(), p.getIdcategoria(),
        p.getPrecioventa(), p.getStock()};
      tabla.addRow(datos);// nagrega a ka tabla
    }
  }

   
}