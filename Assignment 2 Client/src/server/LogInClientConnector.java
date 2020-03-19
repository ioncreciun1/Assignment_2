package server;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class LogInClientConnector implements Runnable
{
  private int PORT = 6969;
  private boolean running;
  private ServerSocket welcomeSocket;
  private Model model;

  public LogInClientConnector(Model model) throws IOException
  {
    this.running = false;
    this.model = model;
    this.welcomeSocket = new ServerSocket(PORT);
  }

  public void stop()
  {

  }

  @Override public void run()
  {
    System.out.println("Waiting for client...");

    while(true)
    {
      try
      {
        Socket socket = welcomeSocket.accept();
        System.out.println("Here is" + socket.getInetAddress().getHostAddress());
        LogInClientHandler studentListClientHandler = new LogInClientHandler(socket, model);
        Thread t = new Thread(LogInClientHandler);
        t.setDaemon(true);
        t.start();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
