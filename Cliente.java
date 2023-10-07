import java.net.*;

class Cliente extends Sockets {

  public Cliente() {
    try {
      Socket skCliente = new Socket(HOST, PUERTO);
      String respuesta = recibirDatos(skCliente);
      System.out.println(respuesta);

      String expresion = System.console().readLine();
      enviarDatos(skCliente, expresion);
      respuesta = recibirDatos(skCliente);
      System.out.println(respuesta);
      skCliente.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] arg) {
    new Cliente();

  }
}