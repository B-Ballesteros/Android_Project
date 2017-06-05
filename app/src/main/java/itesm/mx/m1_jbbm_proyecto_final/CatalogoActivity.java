package itesm.mx.m1_jbbm_proyecto_final;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

public class CatalogoActivity extends ListActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener{

    private final int REQUEST_CODE_COMPRAR = 1;
    int indexLibro;
    int indexCarrito;

    ArrayList<Libro> libros;
    ArrayList<Carrito> carritos;
    LibroAdapter adapterLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        inicializar();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent;
        intent = new Intent(this, CompraActivity.class);
        Libro  libro = libros.get(position);
        intent.putExtra("titulo", libro.getTitulo());
        intent.putExtra("isbn", libro.getIsbn());
        intent.putExtra("cantidad", libro.getCantidadExistencia());
        intent.putExtra("precio", libro.getPrecioUnitario());
        intent.putExtra("imagen", libro.getIdImagen());
        intent.putExtra("indexCarrito", getIndexCarrito(libro.getIsbn()));
        intent.putExtra("carrito", carritos);
        startActivityForResult(intent, REQUEST_CODE_COMPRAR);
        indexLibro = position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_COMPRAR:
                if(resultCode == RESULT_OK){
                    updateLibros(data.getExtras());
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CarritoActivity.class);
        intent.putExtra("carrito", carritos);
        startActivity(intent);
    }

        //region funciones

    private void inicializar(){
        libros = getDataForListView();
        carritos = new ArrayList<>();
        adapterLibros = new LibroAdapter(getApplicationContext(),
                R.layout.row, libros);
        setListAdapter(adapterLibros);
        getListView().setOnItemClickListener(this);
        ((Button)findViewById(R.id.boton_carrito)).setOnClickListener(this);
    }
    private ArrayList<Libro> getDataForListView(){
        Libro libro;
        ArrayList<Libro> listaLibros = new ArrayList<>();

        libro = new Libro("Introduction to Android Application Development: Android Essentials",
                "013438945X", 5, R.drawable.libro_develop, 39.99);
        listaLibros.add(libro);
        libro = new Libro("Android Application Development Cookbook - Second Edition",
                "1785886193", 8, R.drawable.libro_cookbook, 44.99);
        listaLibros.add(libro);
        libro = new Libro("Android Programming for Beginners",
                "9781785889035", 6, R.drawable.libro_beginners, 48.0);
        listaLibros.add(libro);
        libro = new Libro("Learn Java for Android Development",
                "1430264543", 3, R.drawable.libro_java, 56.0);
        listaLibros.add(libro);
        libro = new Libro("Android: App Development & Programming Guide: Learn In A Day",
                "9781329747517", 10, R.drawable.libro_guide, 33.49);
        listaLibros.add(libro);


        return  listaLibros;
    }

    private void updateLibros(Bundle datos){
        libros.get(indexLibro).setCantidadExistencia(datos.getInt("cantidad"));
        carritos = (ArrayList<Carrito>)datos.getSerializable("carrito");
        adapterLibros.notifyDataSetChanged();
    }

    private int getIndexCarrito(String isbn){
        int position = 0;
        for(Carrito carrito : carritos){
            if(carrito.getIsbn().equals(isbn)){
                return position;
            }
            position++;
        }
        return  -1;
    }

    //endregion
}
