package co.udistrital.android.thomasmensageria.entities;

/**
 * Created by wisuarez on 19/02/2018.
 */

public class Product {

    int id;
    String nombre;
    int cliente;
    String estado;


    public Product(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
