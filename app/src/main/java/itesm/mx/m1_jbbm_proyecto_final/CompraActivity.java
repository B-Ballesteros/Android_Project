package itesm.mx.m1_jbbm_proyecto_final;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CompraActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    int cantidad = 0;
    double precio = 0;
    int librosComprados = 0;
    int indexCarrito;
    int idImagen;
    ArrayList<Carrito> carritos;

    TextView tvTitulo;
    TextView tvIsbn;
    TextView tvPrecio;
    TextView tvTotal;
    Spinner spinnerCantidad;
    ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        inicializar();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        librosComprados = Integer.parseInt(spinnerCantidad.getItemAtPosition(pos).toString());
        tvTotal.setText(String.format("$%1$.2f", calcularTotal()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}

    @Override
    public void onClick(View v){
        //setResult(RESULT_OK, comprar());
        insertUpdateCarrito(indexCarrito);

        Intent intent;
        intent = new Intent(this, CompraActivity.class);
        intent.putExtra("cantidad", (cantidad - librosComprados));
        intent.putExtra("carrito", carritos);

        setResult(RESULT_OK, intent);
        finish();
    }

    //region funciones
    private  void inicializar(){
        cargarControles();
        llenarControles(getIntent().getExtras());
        setListeners();
    }

    private void cargarControles(){
        tvTitulo = (TextView)findViewById(R.id.text_titulo);
        tvIsbn = (TextView)findViewById(R.id.text_isbn);
        tvPrecio = (TextView)findViewById(R.id.text_precio);
        tvTotal = (TextView)findViewById(R.id.text_total);

        spinnerCantidad = (Spinner)findViewById(R.id.spinner_cantidad);

        ivImagen =(ImageView)findViewById(R.id.image_libro);

    }

    private void llenarControles(Bundle data){
        if(data == null) { return; }

        precio = data.getDouble("precio", 0);
        cantidad = data.getInt("cantidad", 0);
        indexCarrito = data.getInt("indexCarrito", 0);
        idImagen = data.getInt("imagen", R.mipmap.ic_launcher);
        carritos = (ArrayList<Carrito>)data.getSerializable("carrito");

        tvTitulo.setText(data.getString("titulo"));
        tvIsbn.setText(data.getString("isbn"));
        tvPrecio.setText(String.format("$%1$.2f", precio));
        tvTotal.setText(String.format("$%1$.2f", calcularTotal()));

        spinnerCantidad.setAdapter(new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, getSpinnerItems(cantidad)));

        ivImagen.setImageBitmap(Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(getResources(), idImagen), 120, 150, true));
    }

    private void setListeners(){
        spinnerCantidad.setOnItemSelectedListener(this);
        spinnerCantidad.setSelection(0);
        ((Button)findViewById(R.id.boton_comprar)).setOnClickListener(this);
    }

    private double calcularTotal(){
        return librosComprados * precio;
    }

    private  Integer[] getSpinnerItems(int cantidad){
        Integer[] cantidades = new Integer[cantidad];
        for(int i = 0; i < cantidad; i++){
            cantidades[i] = i + 1;
        }
        return  cantidades;
    }

    private Intent comprar(){
        insertUpdateCarrito(indexCarrito);

        Intent intent;
        intent = new Intent(this, CompraActivity.class);
        intent.putExtra("cantidad", (cantidad - librosComprados));
        intent.putExtra("carrito", carritos);
        return  intent;
    }

    private void insertUpdateCarrito(int posicion){
        if(posicion == -1){
            Carrito carrito = new Carrito(tvIsbn.getText().toString(),
                    tvTitulo.getText().toString(), librosComprados, calcularTotal(), idImagen);
            carritos.add(carrito);
        }else{
            Carrito carrito = carritos.get(posicion);
            carrito.setPrecioTotal(carrito.getPrecioTotal() + calcularTotal());
            carrito.setCantidadCompra(carrito.getCantidadCompra()  +  librosComprados);
        }
    }
    //endRegion
}
