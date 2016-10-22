package cjava.servicio;

public interface IDaoVenta<T> {

    // definir firmas
    int registraVenta(T v) throws Exception;
}
