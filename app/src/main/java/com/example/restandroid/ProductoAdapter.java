package com.example.restandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.restandroid.Modelo.Producto;

import org.w3c.dom.Text;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto>productos;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);
        this.context=context;
        this.productos=objects;
    }


    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        //llamamos a los textView de diseño en estas variables
        TextView txtidProducto=(TextView) rowView.findViewById(R.id.IDProducto);
        TextView txtNombre=(TextView) rowView.findViewById(R.id.Nombre);
        TextView txtCategoria=(TextView) rowView.findViewById(R.id.Categoria);
        TextView txtCantidad=(TextView) rowView.findViewById(R.id.Cantidad);

        //asignamos valores dentro de la caja de texto
        txtidProducto.setText(String.format("ID:%d ",productos.get(position).getId()));
        txtNombre.setText(String.format("NOMBRE: %s",productos.get(position).getNombreProducto()));
        txtCategoria.setText(String.format("CATEGORIA: %s",productos.get(position).getCategoria()));
        txtCantidad.setText(String.format("CANTIDAD: %s",productos.get(position).getCantidad()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductoActivity.class);
                //
                intent.putExtra("ID",String.valueOf(productos.get(position).getId()));
                intent.putExtra("NOMBRE",productos.get(position).getNombreProducto());
                intent.putExtra("CATEGORIA",productos.get(position).getCategoria());
                intent.putExtra("CANTIDAD",productos.get(position).getCantidad());
                context.startActivity(intent);
            }
        });


        return rowView;
    }
}
