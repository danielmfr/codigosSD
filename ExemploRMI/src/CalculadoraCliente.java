import static java.lang.Integer.parseInt;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class CalculadoraCliente {
	
	public static void main(String[] args) throws RemoteException {
		Registry reg = null;
		ICalculadora calc = null;		
		try {
			reg = LocateRegistry.getRegistry(1099);
			calc = (ICalculadora) reg.lookup("calculadora");
			//calc.somar(12, 20);
		} catch (RemoteException | NotBoundException e) {
				System.out.println(e);
				System.exit(0);
		}
		
		Scanner scanner = new Scanner(System.in);
                
                boolean aux = true;
                
		while(aux) {
                    System.out.println("CALCULADORA RMI DD");
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
                            System.out.println("Valor do resultado: " + calc.somar(valor1, valor2) + "\n");
                        }
                        case 2 -> {
                            System.out.println("\nSelecione o valor 1: ");
                            valor1 = parseInt(scanner.nextLine());
                            System.out.println("Selecione o valor 2: ");
                            valor2 = parseInt(scanner.nextLine());
                            System.out.println("Valor do resultado: " + calc.subtrair(valor1, valor2) + "\n");
                        }
                        case 3 -> {
                            System.out.println("\nSelecione o valor 1: ");
                            valor1 = parseInt(scanner.nextLine());
                            System.out.println("Selecione o valor 2: ");
                            valor2 = parseInt(scanner.nextLine());
                            System.out.println("Valor do resultado: " + calc.multiplicar(valor1, valor2) + "\n");
                        }
                        case 4 -> {
                            System.out.println("\nSelecione o valor 1: ");
                            valor1 = parseInt(scanner.nextLine());
                            System.out.println("Selecione o valor 2: ");
                            valor2 = parseInt(scanner.nextLine());
                            System.out.println("Valor do resultado: " + calc.dividir(valor1, valor2) + "\n");
                        }
                        case 5 -> aux = false;
                        default -> throw new AssertionError();
                    }
		}
	}		

}