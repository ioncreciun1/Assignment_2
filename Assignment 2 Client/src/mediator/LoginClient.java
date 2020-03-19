package mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginClient implements ServerModel
{
  public static final String HOST = "localhost";
  public static final int PORT = 1234;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  public LoginClient(String host,int port)
  {
    this.host = host;
    this.port = port;
  }
  public LoginClient()
  {
    this.host = HOST;
    this.port = PORT;
  }

  @Override public void connect() throws IOException
  {
    socket = new Socket(HOST,PORT);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(),true);

  }

  @Override public void disconnect() throws IOException
  {
    socket.close();
    in.close();
    out.close();
  }

  @Override public boolean verifyLog(String password,String name) throws IOException
  {

    out.println(password);
    out.println(name);
    String answer = in.readLine();
    return answer.equals("approved");
  }

}
