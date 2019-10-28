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
		
		List<Player>pegadores = new ArrayList<Player>();
		List<Player>corredores = new ArrayList<Player>();
		
		ThreadVereficaTempo verifica;
		Thread threadVerifica;
		while(true) {
				verifica = new ThreadVereficaTempo(players);
				threadVerifica =  new Thread(verifica);
			System.out.println("inicou");
			//verificaTempo(players);
			threadVerifica.start();
			
			
			try {
				Thread.currentThread().sleep(Main.TEMPO);
				tempo++;
				threadVerifica.stop();
				if(tempo >= 60) {
					System.out.println("Parou");
					for(Player player : players) {
						player.EnviarMsg("Encerrou;0");
						player.closeSocket();
					}
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		for(Player thread : players) {
//			//for each que separa os tipos de jogadores
//			
//			if(thread.getTipo().equals("runner")){
//				corredores.add(thread);
//			}else if(thread.getTipo().equals("catch")){
//				pegadores.add(thread);
//			}					
//		}
//		
//		while(Main.iniciar) {
//			System.out.println("Tempo: "+tempo);
//			//System.out.println(getData());
//			//verificar(players);
//			if(tempo == 60) {
//				//acabar por tempo (1 minuto)
//				Main.iniciar = false;
//				for(Player thread : players) {
//					thread.EnviarMsg(Main.MSGVENCETEMPO);
//					thread.closeSocket();
//				}
//				
//				continue;
//			}
//			
//			if(corredores.isEmpty()) {
//				//envia a resposta de vitoria aos perseguidores caso n�o haja mais corredores
//				for(Player thread : players) {
//					Main.iniciar = false;
//					thread.EnviarMsg(Main.MSGVENCEPEGAR);
//					thread.closeSocket();
//					continue;
//				}
//			}
//			try {
//				//verificaTempo(players);
//				Thread.currentThread().sleep(Main.TEMPO);	
//				for(Player corredor : corredores) {
//					for(Player pegador : pegadores) {
//						
//						if(corredor.getTime() == 0) {
//							corredores.remove(corredor);
//						}
//						
//						if(pegador.getTime() == 0) {
//							pegadores.remove(pegador);
//						}
//						
//						if(corredor.getX() == pegador.getX() && pegador.getY() == corredor.getX() && corredor.getTime() == pegador.getTime()) {
//							//verificacao da position X, Y e tempo. Caso todos batam o corredor foi pego
//								
//							corredores.remove(corredor); 
//							corredor.EnviarMsg(Main.MSGPEGO);
//							
//						}else if((corredor.getX()-1 == pegador.getX() && corredor.getTime() == pegador.getTime()) || (corredor.getY()-1 == pegador.getY() && corredor.getTime() == pegador.getTime())){
//							corredor.EnviarMsg(Main.MSGALERTAPROXIMO);
//						}
//					}
//				}
//				
//				
//				
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			tempo = tempo + 1;
//		}
		
	}
}
