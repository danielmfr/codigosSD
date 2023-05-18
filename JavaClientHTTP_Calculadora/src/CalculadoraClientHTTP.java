
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

    public static void main(String[] args) {

        String result = "";
        try {

            URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //ENVIO DOS PARAMETROS
            String req = requisicao();
            if (req != null) {
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(req); //1-somar 2-subtrair 3-multiplicar 4-dividir
                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    //RECBIMENTO DOS PARAMETROS
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), "utf-8"));
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    result = response.toString();
                    System.out.println("Resposta do Servidor PHP = " + result);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String requisicao() {
        Scanner scanner = new Scanner(System.in);
        boolean aux = true;

        while (aux) {
            System.out.println("CALCULADORA HTTP DD");
            System.out.println("==============================");
            System.out.println("Selecione a operação:");
            System.out.println("1 - Somar");
            System.out.println("2 - Subtrair");
            System.out.println("3 - Multiplicar");
            System.out.println("4 - Dividir");
            System.out.println("5 - Sair");
            System.out.println("Escolha: ");

            int escolha = parseInt(scanner.nextLine());
            int valor1, valor2;
            switch (escolha) {
                case 1 -> {
                    System.out.println("\nSelecione o valor 1: ");
                    valor1 = parseInt(scanner.nextLine());
                    System.out.println("Selecione o valor 2: ");
                    valor2 = parseInt(scanner.nextLine());
                    return "oper1=" + valor1 + "&oper2=" + valor2 + "&operacao=1";
                }
                case 2 -> {
                    System.out.println("\nSelecione o valor 1: ");
                    valor1 = parseInt(scanner.nextLine());
                    System.out.println("Selecione o valor 2: ");
                    valor2 = parseInt(scanner.nextLine());
                    return "oper1=" + valor1 + "&oper2=" + valor2 + "&operacao=2";
                }
                case 3 -> {
                    System.out.println("\nSelecione o valor 1: ");
                    valor1 = parseInt(scanner.nextLine());
                    System.out.println("Selecione o valor 2: ");
                    valor2 = parseInt(scanner.nextLine());
                    return "oper1=" + valor1 + "&oper2=" + valor2 + "&operacao=3";
                }
                case 4 -> {
                    System.out.println("\nSelecione o valor 1: ");
                    valor1 = parseInt(scanner.nextLine());
                    System.out.println("Selecione o valor 2: ");
                    valor2 = parseInt(scanner.nextLine());
                    return "oper1=" + valor1 + "&oper2=" + valor2 + "&operacao=4";
                }
                case 5 ->
                    aux = false;
                default ->
                    throw new AssertionError();
            }
        }
        return null;
    }
}
