package itesm.mx.m1_jbbm_proyecto_final;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by benji on 22/02/17.
 */

public class CarritoAdapter  extends ArrayAdapter<Carrito>{
    ArrayList<Carrito> carritos;
    Context context;

    public CarritoAdapter(Context context, int resource, ArrayList<Carrito> objects){
        super(context, resource, objects);
        this.carritos = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        v  = v == null ? ((LayoutInflater)getContext().getSystemService(
                context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row, null) : v;

        Carrito carrito = carritos.get(position);
        if(carrito != null) {
            TextView tvTitulo = (TextView) v.findViewById(R.id.text_titulo);
            TextView tvIsbn = (TextView) v.findViewById(R.id.text_isbn);
            TextView tvCantidad = (TextView) v.findViewById(R.id.text_cantidad);
            TextView tvPrecio = (TextView) v.findViewById(R.id.text_precio);
            ImageView imageLibro = (ImageView) v.findViewById(R.id.image_libro);

            tvTitulo.setText(String.format("Título  %s", carrito.getTitulo()));
            tvIsbn.setText(String.format("ISBN  %s", carrito.getIsbn()));
            tvCantidad.setText(String.format("Existencias  %d", carrito.getCantidadCompra()));
            tvPrecio.setText(String.format("Precio  $%1$.2f", carrito.getPrecioTotal()));

            imageLibro.setImageBitmap(Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getContext().getResources(),
                            carrito.getIdImagen()), 90, 120, true));
        }
        return  v;
    }
}
