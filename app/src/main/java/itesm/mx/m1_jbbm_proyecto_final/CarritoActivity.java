package itesm.mx.m1_jbbm_proyecto_final;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CarritoActivity extends ListActivity {


    CarritoAdapter carritoAdapter;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        inicializar();
    }

    //region funciones
    private  void  inicializar(){
        cargarControles();
        llenarControles(getIntent().getExtras());
    }
    private void cargarControles(){
        tvTotal = (TextView)findViewById(R.id.text_total);
    }
    private void llenarControles(Bundle data){
        if(data == null) { return; }

        ArrayList<Carrito> carritos =  (ArrayList<Carrito>)data.getSerializable("carrito");

        tvTotal.setText(String.format("$%1$.2f", calcularTotal(carritos)));

        carritoAdapter = new CarritoAdapter(getApplicationContext(),
                R.layout.row, carritos);
        setListAdapter(carritoAdapter);
    }

    private  double calcularTotal(ArrayList<Carrito> carritos){
        double suma = 0;
        for(Carrito carrito : carritos){
            suma += carrito.getPrecioTotal();
        }
        return  suma;
    }
    //endregion
}
