import java.io.PrintWriter;
import java.net.Socket;

public interface Player {
		
	public String getTipo();
	public void leituraMsg();
	public String getTime();
	public Integer getY();
	public Integer getX();
	public void EnviarMsg(String msg);
	public void closeSocket();
	public String getNome();
}
