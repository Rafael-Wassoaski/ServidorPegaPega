import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Jogar {

	public static float tempo = 0;
	public static String getData() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String formato = "Hora: "+ calendar.get(Calendar.HOUR_OF_DAY) +" Minuto: "+ calendar.get(Calendar.MINUTE) + " Segundo: "+calendar.get(Calendar.SECOND);
		return formato;
	}
	
		
	public static void verificar(List<Player> players) {
		
		try {
		
		List<Player>pegadores = new ArrayList<Player>();
		List<Player>corredores = new ArrayList<Player>();
		
		ThreadVereficaTempo verifica;
		Thread threadVerifica;
		System.out.println("inicou");
		while(true) {
				verifica = new ThreadVereficaTempo(players);
				threadVerifica =  new Thread(verifica);
			
			//verificaTempo(players);
			threadVerifica.start();
			
			
			try {
				System.out.println("Tempo: "+tempo);
				Thread.currentThread().sleep(Main.TEMPO);
				threadVerifica.stop();
				
				tempo++;
				if(tempo >= 60) {
					System.out.println("Parou");
					for(Player player : players) {
						player.EnviarMsg("Encerrou;"+Main.MSGVENCETEMPO);
						player.closeSocket();
					}
					
				}
				
				
				for(Player player : players ) {
					if(player.getTipo().equals("runner")) {
						corredores.add(player);
					}else {
						pegadores.add(player);
					}
				}
				
				if(corredores.isEmpty()) {
					//envia a resposta de vitoria aos perseguidores caso nï¿½o haja mais corredores
					for(Player thread : players) {
						Main.iniciar = false;
						thread.EnviarMsg("Encerrou;"+Main.MSGVENCEPEGAR);
						thread.closeSocket();
						continue;
					}
				}
				for(Player corredor : corredores) {
					for(Player pegador : pegadores) {
						
						if(corredor.getTime() == 0) {
							players.remove(corredor);
							corredor.EnviarMsg("Encerrou;Você foi desconectado");
							break;
						}
						
						if(pegador.getTime() == 0) {
							players.remove(pegador);
							pegador.EnviarMsg("Encerrou;Você foi desconectado");
							break;
						}
						
						if(corredor.getX() == pegador.getX() && pegador.getY() == corredor.getX() && corredor.getTime() == pegador.getTime()) {
							//verificacao da position X, Y e tempo. Caso todos batam o corredor foi pego
								
							players.remove(corredor); 
							corredor.EnviarMsg("Encerrou;"+Main.MSGPEGO);
							break;
							
						}else if((corredor.getX()-1 == pegador.getX() && corredor.getTime() == pegador.getTime()) || (corredor.getY()-1 == pegador.getY() && corredor.getTime() == pegador.getTime())){
							corredor.EnviarMsg("Proximo;"+Main.MSGALERTAPROXIMO);
							break;
						}
					}
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}

		
	}
}
