package com.example.restandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restandroid.Modelo.Producto;
import com.example.restandroid.Utils.Apis;
import com.example.restandroid.Utils.ProductoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoActivity extends AppCompatActivity {

    ProductoService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_layout);


        //agrego los valores PRODUCTO LAYOUT a una variable
        TextView idpro=(TextView)findViewById(R.id.tvid);
        EditText txtId=(EditText)findViewById(R.id.txtId);
        TextView nombre=(TextView) findViewById(R.id.tvnombre);
        EditText txtNombre=(EditText) findViewById(R.id.txtNombre);
        TextView categoria=(TextView) findViewById(R.id.tvcategoria);
        EditText txtCategoria=(EditText) findViewById(R.id.txtcategoria);
        TextView Cantidad=(TextView) findViewById(R.id.tvcantidad);
        EditText txtCantidad=(EditText)findViewById(R.id.txtcantidad);



        Button btnSave=(Button) findViewById(R.id.btnSave);
        Button btnVolver=(Button) findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

        //updatE VARIABLES
        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom= bundle.getString("NOMBRE");
        String cat= bundle.getString("CATEGORIA");
        String cant= bundle.getString("CANTIDAD");

        //SETEAMOS LAS VARIABLES
        txtId.setText(id);
        txtNombre.setText(nom);
        txtCategoria.setText(cat);
        txtCantidad.setText(cant);
        //SI ES UPDATE BLOQUEA CAMPO ID
        if(id.trim().length()==0||id.equals("")){
            idpro.setVisibility(View.INVISIBLE);//se le setea un valor id(INVISIBLE)
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto();
                p.setNombreProducto(txtNombre.getText().toString());
                p.setCategoria(txtCategoria.getText().toString());
                String valorcant=txtCantidad.getText().toString();
                p.setCantidad(Integer.parseInt(valorcant));
                if(id.trim().length()==0||id.equals("")){
                    addProducto(p);
                }else{
                    updateProducto(p,Integer.valueOf(id)); //convertimos a entero el id
                    Intent intent =new Intent(ProductoActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                addProducto(p);
                Intent intent=new Intent(ProductoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //funcionalidad al boton eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProducto(Integer.valueOf(id)); //convertimos de texto a int
                Intent intent=new Intent(ProductoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //funcionalidad boton volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addProducto(Producto p){
        service = Apis.getProductoService();
        Call<Producto>call=service.addProducto(p);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response!=null){
                    Toast.makeText(ProductoActivity.this,"Se agrego con exito",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    //update diplica el metodo insertar
    //cambia nombre
    public void updateProducto(Producto p,int id){
        service = Apis.getProductoService();
        Call<Producto>call=service.updateProducto(p,id);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response!=null){
                    Toast.makeText(ProductoActivity.this,"Se actualizo con exito",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    public void deleteProducto(int id){
        service=Apis.getProductoService();
        Call<Producto>call=service.deleteProducto(id);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductoActivity.this,"Se elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e("Error:",t.getMessage());

            }
        });
        Intent intent=new Intent(ProductoActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
