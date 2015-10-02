package co.edu.dmi.monk.singleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer {

    private String textoEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Comunicacion.getInstance().setObserver(this);

        Button conectar  = (Button)findViewById(R.id.btn_conectar);
        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().conectar();
            }
        });

        Button saludar  = (Button)findViewById(R.id.btn_saludar);
        saludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().saludar("Saludando desde la pantalla principal");
            }
        });

        Button cambiar  = (Button)findViewById(R.id.btn_cambiar);
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActividadSecundaria.class);
                startActivity(i);
            }
        });
    }

    public void updateUI(String cambio){
        textoEstado = cambio;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView estado = (TextView) findViewById(R.id.txt_estado_conexion);
                estado.setText(textoEstado);
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        if(data instanceof String){
            String s = (String)data;
            updateUI(s);
        }
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
