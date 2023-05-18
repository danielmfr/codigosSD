import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerSocket welcomeSocket;
        DataOutputStream socketOutput;
        DataInputStream socketInput;
        BufferedReader socketEntrada;
        Calculadora calc = new Calculadora();
        try {
            welcomeSocket = new ServerSocket(9090);
            int i = 0; //número de clientes

            System.out.println("Servidor no ar");
            while (true) {

                Socket connectionSocket = welcomeSocket.accept();
                i++;
                System.out.println("Nova conexão");

                //Interpretando dados do servidor
                socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String operacao = socketEntrada.readLine();
                double oper1 = Double.parseDouble(socketEntrada.readLine());
                double oper2 = Double.parseDouble(socketEntrada.readLine());
                String result = "";

                // Verifica a operação e chama a calculadora
                switch (operacao) {
                    case "1" -> result = "" + calc.somar(oper1, oper2);
                    case "2" -> result = "" + calc.subtrair(oper1, oper2);
                    case "3" -> result = "" + calc.multiplicar(oper1, oper2);
                    case "4" -> result = "" + calc.dividir(oper1, oper2);
                    default -> {
                    }
                }

                //Enviando dados para o servidor
                socketOutput = new DataOutputStream(connectionSocket.getOutputStream());
                socketOutput.writeBytes(result + '\n');
                System.out.println(result);
                socketOutput.flush();
                socketOutput.close();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
