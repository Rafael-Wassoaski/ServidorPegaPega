import java.util.List;

public class ThreadVereficaTempo implements Runnable {
	
	
	private List<Player> players;
	public  ThreadVereficaTempo(List<Player> players) {
		// TODO Auto-generated constructor stub
		this.players = players;
	}

	@Override
	public void run() {
		try {
		float attTime = Jogar.tempo;
		
		for(Player player : players) {
			if(player != null) {
			
				if(attTime < player.getTime()) {
					System.out.println("Um tempo incorreto: " + player.getNome());
					
					Jogar.tempo = attTime = player.getTime();
				}
			}
		}
		
		for(Player player : players) {
			player.EnviarMsg("tempo;"+attTime);
		
		} 
		} catch(NullPointerException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}		
	}


