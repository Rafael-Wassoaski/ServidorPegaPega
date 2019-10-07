import java.util.ArrayList;
import java.util.List;

public class Jogar {
	
		
	public static void verificar(List<Player> players) {
		
		while(Main.INICIAR) {
			try {
				Thread.currentThread().sleep(Main.tempo);
				List<Player>pegadores = new ArrayList<Player>();
				List<Player>corredores = new ArrayList<Player>();
				ThreadJogador player;
				for(Player thread : players) {
					
					if(thread.getTipo().equals("runner")){
						corredores.add(thread);
					}else if(thread.getTipo().equals("catch")){
						pegadores.add(thread);
					}
					
					for(Player corredor : corredores) {
						for(Player pegador : pegadores) {
							if()
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
