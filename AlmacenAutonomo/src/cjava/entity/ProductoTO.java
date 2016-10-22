package cjava.entity;

public class ProductoTO {

  private String idproducto;
  private String descripcion;
  private int Idcategoria;
  private double precioventa;
  private double preciocompra;
  private int stock;

  public ProductoTO() {

  }

  public ProductoTO(String idproducto, String descripcion, int idcategoria,
          double precioventa, double priciocompra, int stock) {
    this.idproducto = idproducto;
    this.descripcion = descripcion;
    Idcategoria = idcategoria;
    this.precioventa = precioventa;
    this.preciocompra = priciocompra;
    this.stock = stock;
  }

  public String getIdproducto() {
    return idproducto;
  }

  public void setIdproducto(String idproducto) {
    this.idproducto = idproducto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getIdcategoria() {
    return Idcategoria;
  }

  public void setIdcategoria(int idcategoria) {
    Idcategoria = idcategoria;
  }

  public double getPrecioventa() {
    return precioventa;
  }

  public void setPrecioventa(double precioventa) {
    this.precioventa = precioventa;
  }

  public double getPreciocompra() {
    return preciocompra;
  }

  public void setPreciocompra(double preciocompra) {
    this.preciocompra = preciocompra;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return idproducto + " " + descripcion ;
  }

  
  
}
