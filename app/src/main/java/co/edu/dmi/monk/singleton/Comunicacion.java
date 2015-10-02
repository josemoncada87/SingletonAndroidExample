package co.edu.dmi.monk.singleton;

import android.app.Activity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observer;

/**
 * Created by jamoncada on 30/09/2015.
 */
public class Comunicacion extends Thread {

    private static Comunicacion ref;
    private Socket s;
    private Observer jefe;
    private boolean activado;

    private Comunicacion() {
        s =  null;
        activado = false;
    }


    @Override
    public void run() {
        while (true) {
            if (s == null && activado) {
                try {
                    s = new Socket(InetAddress.getByName("10.0.2.2"), 5000);
                    notificar("conectado");
                } catch (IOException e) {
                    e.printStackTrace();
                    notificar("no fue posible conectarse");
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Comunicacion getInstance() {
        if (ref == null) {
            ref = new Comunicacion();
            ref.start();
        }
        return ref;
    }

    public void conectar() {
        if(!activado){
            notificar("ya se encuentra en proceso de conexion");
        }
        activado =  true;
    }

    public void saludar(String mensaje) {
            if (this.s != null && activado) {
                try {
                    DataOutputStream salida = new DataOutputStream(this.s.getOutputStream());
                    salida.writeUTF(mensaje);
                    salida.flush();
                    notificar("Saludé");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        if(!activado){
            notificar("no está conectado con el servidor, no puede saludar");
        }
    }

    public void setObserver(Observer ac) {
        jefe = ac;
    }

    public void notificar(String cambio){
        jefe.update(null, cambio);
    }
}
