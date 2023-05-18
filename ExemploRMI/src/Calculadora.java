import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadasSoma = 0;
	private static int chamadasSubstrair = 0;
	private static int chamadasMultiplicar = 0;
	private static int chamadasDividir = 0;

        @Override
	public int somar(int a, int b) throws RemoteException {
		System.out.println("Método somar chamado " + ++chamadasSoma);
		return a + b;
	}
	
        @Override
	public int subtrair(int a, int b) throws RemoteException {
		System.out.println("Método subtrair chamado " + ++chamadasSubstrair);
		return a - b;
	}
	
        @Override
	public int multiplicar(int a, int b) throws RemoteException {
		System.out.println("Método multiplicar chamado " + ++chamadasMultiplicar);
		return a * b;
	}
	
        @Override
	public int dividir(int a, int b) throws RemoteException {
		System.out.println("Método dividir chamado " + ++chamadasDividir);
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		Calculadora calculadora = new Calculadora();		
		Registry reg = null;
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub);
	}
}
