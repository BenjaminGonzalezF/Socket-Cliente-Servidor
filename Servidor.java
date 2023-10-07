import java.io.*;
import java.net.* ;

import javax.sound.sampled.SourceDataLine;


class Servidor {


  static final int PUERTO = 5000;
  static final int maxClientes = 10;

  public Servidor(){
    try {
      ServerSocket skServidor = new ServerSocket( PUERTO );
      System.out.println("Escucho el puerto " + PUERTO );


      for (int numCli = 0; numCli < maxClientes; numCli++) {
        Socket skCliente = skServidor.accept();   
        OutputStream aux = skCliente.getOutputStream();
        System.out.println("Conexion con el cliente  "+numCli);


        DataOutputStream outputServer= new DataOutputStream( aux );
        
        outputServer.writeUTF( "Ingrese una expresion a operar formato: a operacion b" );
        
        InputStream aux2 = skCliente.getInputStream();
        DataInputStream inputServer = new DataInputStream(aux2);
        System.out.println("El cliente envio la expresion: ");
        String expresion = inputServer.readUTF();
        System.out.println(expresion);
        parsear(expresion);
        skCliente.close();
      }
      System.out.println("Demasiados clientes por hoy");
    } catch( Exception e ) {
      System.out.println( e.getMessage() );
    }
  }
/*   private void enviarDatos(Socket socketCliente ){
    
    DataOutputStream outputServer= new DataOutputStream(socketCliente );  
    outputServer.writeUTF( "Ingrese una expresion a operar formato: a operacion b" );
a
  } */


  private void parsear(String expresion){

    String[] expresionDividida = expresion.split(" ");
    long a = Long.parseLong(expresionDividida[0]);
    long b = Long.parseLong(expresionDividida[2]);
    String operacion = expresionDividida[1];

    System.out.println("El resultado es: " + operar(operacion, a, b));

  }


  private long operar(String operacion, long a, long b){
    switch(operacion){
      case "+":
        return a+b;
      case "-":
        return a-b;
      case "*":
        return a*b;
      case "/":
        return a/b;
      default:
        System.out.println("Error");
        return 0;
    }
  }
  
  public static void main( String[] arg ) {
    new Servidor();
  }
}