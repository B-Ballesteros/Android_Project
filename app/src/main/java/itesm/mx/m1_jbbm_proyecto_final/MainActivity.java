package itesm.mx.m1_jbbm_proyecto_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    Button btnComprar;

    //region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    @Override
    public void onClick(View v){
        iniciarComprar();
    }
    //endregion

    //region funciones

    private void inicializar(){
        cargarControles();
        setListeners();
    }

    private void cargarControles(){
        etNombre = (EditText)findViewById(R.id.edit_nombre);
        btnComprar = (Button)findViewById(R.id.boton_comprar);
    }

    private void setListeners(){
        btnComprar.setOnClickListener(this);
    }

    private void iniciarComprar(){
        if(esValido()){
            startActivity(new Intent(MainActivity.this, CatalogoActivity.class));
        }else {
            Toast.makeText(this.getApplicationContext(),
                    "ERROR: Debe ingresar su nombre para iniciar", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean esValido(){
        String text = etNombre.getText().toString();
        return !(text.trim().length() <= 0);
    }
    //endregion
}
