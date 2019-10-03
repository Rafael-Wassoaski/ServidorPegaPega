import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	
	public static Runnable setPlayer(String msg, Socket socket) {
		
		Runnable runnable = null;
		
		if(msg.equals("runner;ok")) {
			System.out.println("Novo pegado");
			runnable = new Pegador(socket);
		}else if(msg.equals("catch;ok")){
			System.out.println("Novo pegador");
			runnable = new Pegado(socket);
		}
		
		return runnable;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket a = new ServerSocket(4261);
			
			Runnable runnable1 = null;
			Runnable runnable2 = null;
			
			System.out.println("Aguarde");
			
			Scanner scanner;
			String msg;
			Socket conexao1 = a.accept();
			
			scanner = new Scanner(conexao1.getInputStream());
			
			msg = scanner.nextLine();
			
			runnable1 = setPlayer(msg ,conexao1);
			
			Thread thread1 = new Thread(runnable1);
			thread1.run();
			
			
			
			Socket conexao2 = a.accept();
			
			scanner = new Scanner(conexao2.getInputStream());
			
			msg = scanner.nextLine();
			
			runnable2 = setPlayer(msg ,conexao2);

			Thread thread2 = new Thread(runnable2);
			thread2.run();
			
		
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
