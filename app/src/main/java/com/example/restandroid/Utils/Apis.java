package com.example.restandroid.Utils;

//clase que crea el servicio web
public class Apis {

    public static final String URL_001="http://192.168.1.10:8080/producto/";

    public static ProductoService getProductoService(){
        return Cliente.getCliente(URL_001).create(ProductoService.class);
    }
}
