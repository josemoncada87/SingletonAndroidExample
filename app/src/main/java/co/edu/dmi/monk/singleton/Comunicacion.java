package co.edu.dmi.monk.singleton;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by 1130613425 on 30/09/2015.
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
            if(s == null){
                try {
                   conectar();
                   sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
}
