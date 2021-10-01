package com.example.restandroid.Utils;

import com.example.restandroid.Modelo.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductoService {

    @GET("listar")
    Call<List<Producto>> getProductos();

    @POST("agregar")
    Call<Producto>addProducto(@Body Producto producto);

    //update copiar de addProducto
    @PUT("actualizar/{id}")
    Call<Producto>updateProducto(@Body Producto producto, @Path("id")int id); //enviamos la valiare en el path

    @POST("eliminar/{id}")
    Call<Producto>deleteProducto(@Path("id")int id);
}