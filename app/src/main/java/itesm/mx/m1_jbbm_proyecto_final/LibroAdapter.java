package itesm.mx.m1_jbbm_proyecto_final;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
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

public class LibroAdapter extends ArrayAdapter<Libro> {
    ArrayList<Libro> libros;
    Context context;

    public LibroAdapter(Context context, int resource, ArrayList<Libro> objects){
        super(context, resource, objects);
        this.libros = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        v  = v == null ? ((LayoutInflater)getContext().getSystemService(
                context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row, null) : v;

        Libro libro = libros.get(position);
        if(libro != null) {
            TextView tvTitulo = (TextView) v.findViewById(R.id.text_titulo);
            TextView tvIsbn = (TextView) v.findViewById(R.id.text_isbn);
            TextView tvCantidad = (TextView) v.findViewById(R.id.text_cantidad);
            TextView tvPrecio = (TextView) v.findViewById(R.id.text_precio);
            ImageView imageLibro = (ImageView) v.findViewById(R.id.image_libro);

            tvTitulo.setText(String.format("Título  %s", libro.getTitulo()));
            tvIsbn.setText(String.format("ISBN  %s", libro.getIsbn()));
            tvCantidad.setText(String.format("Existencias  %d", libro.getCantidadExistencia()));
            tvPrecio.setText(String.format("Precio  $%1$.2f", libro.getPrecioUnitario()));

            imageLibro.setImageBitmap(Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(getContext().getResources(), libro.getIdImagen()),
                    90, 120, true));
        }
        return  v;
    }
}
