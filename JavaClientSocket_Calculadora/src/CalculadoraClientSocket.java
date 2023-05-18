import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClientSocket {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CALCULADORA SOCKET DD");
        System.out.println("==============================");
        System.out.println("Selecione a operação:");
        System.out.println("1 - Somar");
        System.out.println("2 - Subtrair");
        System.out.println("3 - Multiplicar");
        System.out.println("4 - Dividir");
        System.out.println("5 - Sair");
        System.out.println("Escolha: ");

        int operacao = scanner.nextInt();
        double oper1, oper2;
        System.out.println("\nSelecione o valor 1: ");
        oper1 = scanner.nextDouble();
        System.out.println("Selecione o valor 2: ");
        oper2 = scanner.nextDouble();
        
        String result = "";
        try {
            //Conexão com o Servidor
            Socket clientSocket = new Socket("192.168.1.64", 9090);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviando os dados
            socketSaidaServer.writeBytes(operacao + "\n");
            socketSaidaServer.writeBytes(oper1 + "\n");
            socketSaidaServer.writeBytes(oper2 + "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            result = messageFromServer.readLine();

            System.out.println("Resultado = " + result);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
