package server;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LogInClientConnector implements Runnable
{
  private int PORT = 1234;
  private boolean running;
  private ServerSocket welcomeSocket;
  private Model model;

  public LogInClientConnector(Model model)
  {
    this.model = model;
  }

  public void start() throws IOException
  {
    model.addLog("Starting Server...");
    welcomeSocket = new ServerSocket(PORT);

    while(true)
    {
      try
      {
        Socket socket = welcomeSocket.accept();

        LogInClientHandler logInClientHandler = new LogInClientHandler(socket, model);
        Thread thread = new Thread(logInClientHandler);
        thread.setDaemon(true);
       thread.start();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

  }

  @Override public void run()
  {
    try
    {
      start();
    }
    catch (IOException e)
    {
      model.addLog("Error : " + e.getMessage());
    }
  }
  public void stop()
  {
    running = false;
   // closeAll();
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }
}
