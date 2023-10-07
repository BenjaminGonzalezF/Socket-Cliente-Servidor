import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Sockets {
    static final int PUERTO = 5000;
    static final int maxClientes = 10;
    static final String HOST = "localhost";

    protected void enviarDatos(Socket socketCliente, String mensaje) {
        try {
            OutputStream outPutCliente = socketCliente.getOutputStream();
            DataOutputStream outputServer = new DataOutputStream(outPutCliente);
            outputServer.writeUTF(mensaje);

        } catch (IOException e) {
            System.out.println("Error al enviar datos ");
            e.printStackTrace();
        }
    }

    protected String recibirDatos(Socket socketCliente) {
        try {
            InputStream aux2 = socketCliente.getInputStream();
            DataInputStream inputServer = new DataInputStream(aux2);
            String respuesta = inputServer.readUTF();
            return respuesta;
        } catch (IOException e) {
            System.out.println("Error al recibir datos ");
            e.printStackTrace();
            return "Error";
        }
    }
}
