import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static List<Player> players;
	public static Boolean iniciar = false;
	public static Integer QUANTIDADEPLAYER = 1;
	final public static Integer TEMPO = 1000;
	final public static String MSGVENCETEMPO  = "O jogo acabou por tempo. Os corredores venceram";
	final public static String MSGVENCEPEGAR  = "O jogo acabou pois todos os corredores foram pegos. Os perseguidores venceram";
	final public static String MSGPEGO  = "Voce foi pego. O Jogo acabou para voce, mas seu parceiro ainda pode vencer";
	final public static String MSGALERTAPROXIMO = "Um jogador está a uma posicao de voce";
	
	
	
	public static void setPlayer( Socket socket) throws IOException {
		
		Integer aliveThreads = 0;
		
		ThreadJogador playerThread = null;
		Thread thread;
		Scanner scanner = new Scanner(socket.getInputStream());
		
		String msg = scanner.nextLine();
		
		
	if(players.size() == Main.QUANTIDADEPLAYER-1) {
		Main.iniciar = true;
	}
	
		if(msg.equals("runner")) {
			System.out.println("Novo corredor");
			playerThread = new ThreadJogador(socket, "runner", "runner "+players.size()+1);
			thread = new Thread(playerThread);
			players.add(playerThread);
			thread.start();
			
			
		}else if(msg.equals("catch")){
			System.out.println("Novo pegador");
			playerThread = new ThreadJogador(socket, "catch",  "catch "+players.size()+1);
			thread = new Thread(playerThread);
			players.add(playerThread);
			thread.start();
		
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
			try (ServerSocket listener = new ServerSocket(4261)) {
				players = new ArrayList<>();
	            System.out.println("Servidor rodando");
	            Socket socket;
	            while (!iniciar) {
	            	socket = listener.accept();
	            	setPlayer(socket);
	            	
	            }
	            System.out.println("Iniciando jogo");
	            
	            Jogar.verificar(players);
	        }catch (Exception e) {
				// TODO: handle exception
	        	System.out.println(e.getMessage());
			}
			
		
			
			
			
		

	}

}
