package com.example.restandroid;

import android.content.Intent;
import android.os.Bundle;

import com.example.restandroid.Modelo.Producto;
import com.example.restandroid.Utils.Apis;
import com.example.restandroid.Utils.ProductoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restandroid.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProductoService productoService;
    List<Producto>listProduct= new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView) findViewById(R.id.listView);

        listarProductos();

        FloatingActionButton fab = findViewById(R.id.fabe); //boton flotante
        fab.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this,ProductoActivity.class);
            //instancia update
            intent.putExtra("ID","");
            intent.putExtra("NOMBRE","");
            intent.putExtra("CATEGORIA","");
            intent.putExtra("CANTIDAD","");

            startActivity(intent);
        }));
    }

    public void listarProductos(){

        productoService=Apis.getProductoService();

        Call<List<Producto>>call=productoService.getProductos();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    //nos muestra si el app corrio con exito
                    listProduct = response.body();
                    listView.setAdapter(new ProductoAdapter(MainActivity.this, R.layout.content_main, listProduct));
                }
            }


            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                //nos muestra el error
                Log.e("Error: ",t.getMessage());

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}