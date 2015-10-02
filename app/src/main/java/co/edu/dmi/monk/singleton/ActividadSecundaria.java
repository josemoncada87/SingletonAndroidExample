package co.edu.dmi.monk.singleton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadSecundaria extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_secundaria);

        Button segundoSaludo = (Button) findViewById(R.id.btn_saludar_otra_pantalla);
        segundoSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicacion.getInstance().saludar("Saludando desde otra pantalla");
            }
        });

        Button regresar = (Button) findViewById(R.id.btn_regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
