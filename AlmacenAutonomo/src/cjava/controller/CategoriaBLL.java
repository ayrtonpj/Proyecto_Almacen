package cjava.controller;

import cjava.dao.CategoriaDAO;
import cjava.entity.CategoriaTO;
import java.util.List;

public class CategoriaBLL {

  CategoriaDAO dao;

  public CategoriaBLL() {
    dao = new CategoriaDAO();
  }

  public List<CategoriaTO> ListaCategorias() throws Exception {
    return dao.readAll();
  }

}
