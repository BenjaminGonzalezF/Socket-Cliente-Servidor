import java.net.*;

class Servidor extends Sockets {

  private long a = 0;
  private long b = 0;
  private String operacion = "";

  public Servidor() {
    try {
      ServerSocket skServidor = new ServerSocket(PUERTO);
      System.out.println("Escucho el puerto " + PUERTO);

      Socket skCliente = skServidor.accept();

      System.out.println("Conexion con el cliente  ");
      enviarDatos(skCliente, "Ingrese una expresion a operar formato: a operacion b");

      String expresion = recibirDatos(skCliente);

      parsear(expresion);
      Long resultado = operar(operacion, a, b);
      System.out.println("El resultado es " + resultado);

      enviarDatos(skCliente, Long.toString(resultado));
      skCliente.close();

      skServidor.close();
      System.out.println("Cliente atendido");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void parsear(String expresion) {
    System.out.println("La expresion es " + expresion);
    String[] expresionDividida = expresion.split("[-+*/]");

    this.a = Long.parseLong(expresionDividida[0].trim());
    this.b = Long.parseLong(expresionDividida[1].trim());

    this.operacion = expresion.replaceAll("[0-9]", "").trim();
  }

  private long operar(String operacion, long a, long b) {
    switch (operacion) {
      case "+":
        return a + b;
      case "-":
        return a - b;
      case "*":
        return a * b;
      case "/":
        return a / b;
      default:
        System.out.println("Error ingrese una operacion valida");
        return 0;
    }
  }

  public static void main(String[] arg) {
    new Servidor();
  }
}