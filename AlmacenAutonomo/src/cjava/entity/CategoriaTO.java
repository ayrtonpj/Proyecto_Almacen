
package cjava.entity;

public class CategoriaTO {
  //
  private int idcategoria;
  private String nombre;
  private String descripcion;
  //

  public CategoriaTO() {
  }

  public CategoriaTO(int idcategoria, String nombre, String descripcion) {
    this.idcategoria = idcategoria;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  //

  public int getIdcategoria() {
    return idcategoria;
  }

  public void setIdcategoria(int idcategoria) {
    this.idcategoria = idcategoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  // redefinir metodo toString

  @Override
  public String toString() {
    return nombre;
  }
  
}
