package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class LoginClient implements ServerModel
{
  public static final String HOST = "localhost";
  public static final int PORT = 1234;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private boolean running;
  private String user;
  private ClientReciver clientReciver;
  public LoginClient(String host,int port)
  {
    this.host = host;
    this.port = port;
  }
  public LoginClient(Model model) throws IOException
  {
    this.host = HOST;
    this.port = PORT;
    this.model = model;
    this.running = false;
    socket = new Socket(HOST,PORT);

  }



  @Override public void disconnect() throws IOException
  {
    socket.close();
    in.close();
    out.close();
  }

  @Override public void connect() throws IOException
  {
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(),true);

  }

  @Override public boolean verifyLog(String password,String name) throws IOException
  {
    out.println(password);
    out.println(name);
    this.user = name;
    String answer = in.readLine();
    if(answer.equals("approved"))
    {
      clientReciver = new ClientReciver(this, in);
      Thread thread = new Thread(clientReciver);
      thread.start();
      return true;
    }
    else {
      return false;
    }
  }


  @Override public void setMessage(String message) throws IOException
  {

    model.setMessage(message);
  }

  @Override public void sendMessage(String message) throws IOException
  {
    Gson gson = new Gson();

    Message sentMessage = new Message("message",message,user);
    String json = gson.toJson(sentMessage);
      out.println(json);


  }
}
