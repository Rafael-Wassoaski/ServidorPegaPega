import java.util.ArrayList;
import java.util.List;

public class Jogar {
	
		
	public static void verificar(List<Player> players) {
		
		Integer tempo = 0;
		List<Player>pegadores = new ArrayList<Player>();
		List<Player>corredores = new ArrayList<Player>();
		for(Player thread : players) {
			//for each que separa os tipos de jogadores
			
			if(thread.getTipo().equals("runner")){
				corredores.add(thread);
			}else if(thread.getTipo().equals("catch")){
				pegadores.add(thread);
			}					
		}
		
		
		while(Main.INICIAR) {
			if(tempo == 60) {
				//acabar por tempo (1 minuto)
				Main.INICIAR = false;
				for(Player thread : players) {
					
					thread.EnviarMsg(Main.msgVencetempo);
					thread.closeSocket();
				}
				
				continue;
			}
			
			if(corredores.isEmpty()) {
				//envia a resposta de vitoria aos perseguidores caso não haja mais corredores
				for(Player thread : players) {
					thread.EnviarMsg(Main.msgVencePegar);
					thread.closeSocket();
					Main.INICIAR = false;
					continue;
				}
			}
			try {
				Thread.currentThread().sleep(Main.tempo);
				tempo = tempo + 1;
				
				ThreadJogador player;
				
				
			
			
				
				for(Player corredor : corredores) {
					for(Player pegador : pegadores) {
						if(corredor.getX() == pegador.getX() && pegador.getY() == corredor.getX() && corredor.getTime() == pegador.getTime()) {
							//verificacao da position X, Y e tempo. Caso todos batam o corredor foi pego
								
							players.remove(corredor); 
							corredor.EnviarMsg(String.format(Main.msgPego, pegador.getNome()));
							
						}else if((corredor.getX()-1 == pegador.getX() ) ){
							
						}
					}
				}
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
