package co.edu.dmi.monk.singleton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by jamoncada on 30/09/2015.
 */
public class Comunicacion extends Thread {

    private static Comunicacion ref;
    private Socket s;

    private Comunicacion(){
        System.out.println("single...");
    }


    @Override
    public void run() {
        while (true){
                try {
                   conectar();
                   sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    public static Comunicacion getInstance() {
        if(ref == null){
            ref =  new Comunicacion();
        }
        return ref;
    }

    public void conectar() {
        if(s == null){
            try {
                s =  new Socket(InetAddress.getByName("10.0.2.2"),5000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saludar() {
        if(s == null) {
            try {
                DataOutputStream salida =  new DataOutputStream(s.getOutputStream());
                salida.writeUTF("Saludo :)");
                salida.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //salida.close();
            //s = null;
        }
    }
}
