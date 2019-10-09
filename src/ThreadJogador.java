import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadJogador implements Runnable, Player{
	
	private Socket socket;
	private String nome;
	private String tipo;
	private PrintWriter printWriter;
	private Integer x;
	private Integer y;
	private String time;
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public ThreadJogador(Socket socket, String tipo, String nome) {
		super();
		this.socket = socket;
		this.tipo = tipo;
		this.nome = nome;
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	public String getTipo() {
		return tipo;
	}

	public void leituraMsg() {
		
		Scanner scanner;
		try {
			scanner = new Scanner (socket.getInputStream());
			String [] msg = scanner.nextLine().split(";");
			
			time = msg[0];
			x = Integer.parseInt(msg [1]);
			y = Integer.parseInt(msg[2]);
			
			System.out.println(time +";"+x+";"+y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public void EnviarMsg(String msg) {
		printWriter.println(msg);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		EnviarMsg("Seu nome;"+getNome());
		
		while(!Main.INICIAR) {
			EnviarMsg("Aguardo;"+getTipo());
			
				try {
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
		
		
		EnviarMsg("Iniciar;"+getTipo());
		while(Main.INICIAR) {
		
			try {
				leituraMsg();
				Thread.currentThread().sleep(Main.tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}




	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		System.out.println("Tempo "+ this.getNome()+" : " + time);
		return time;
	}




	@Override
	public Integer getY() {
		// TODO Auto-generated method stub
		System.out.println("Y "+ this.getNome()+" : " + y);
		return y;
	}




	@Override
	public Integer getX() {
		// TODO Auto-generated method stub
		System.out.println("X "+ this.getNome()+" : " + x);
		return x;
	}
	@Override
	public void closeSocket() {
		// TODO Auto-generated method stub
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
