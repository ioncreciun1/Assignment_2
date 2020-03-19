package server;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogInClientConnector implements Runnable
{
  private int PORT = 5544;
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
    System.out.println("Starting Server...");
    System.out.println("Waiting for client...");

    while(true)
    {
      try
      {

        Socket socket = welcomeSocket.accept();
        System.out.println("Here is " + socket.getInetAddress().getHostAddress());
        LogInClientHandler logInClientHandler = new LogInClientHandler(socket, model);
        Thread t = new Thread(logInClientHandler);
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
