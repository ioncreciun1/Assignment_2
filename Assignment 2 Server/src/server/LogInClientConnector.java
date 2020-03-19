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
  private static ThreadGroup group = new ThreadGroup("ClientHandlerThreads");

  public LogInClientConnector(Model model) throws IOException
  {
    this.model = model;
  }

  public void start() throws IOException
  {
    model.addLog("Starting Server...");
    welcomeSocket = new ServerSocket(PORT);


    running = true;
    while(running)
    {
      try
      {
        model.addLog("Waiting for a client....");
        Socket socket = welcomeSocket.accept();
        LogInClientHandler logInClientHandler = new LogInClientHandler(socket, model,group);
       logInClientHandler.start();
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
    closeAll();
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

  public static boolean closeAll()
  {
    boolean closed = true;
    LogInClientHandler[] threads = new LogInClientHandler[group
        .activeCount()];
    group.enumerate(threads);
    for (LogInClientHandler t : threads)
    {
      try
      {
        t.close();
      }
      catch (Exception e)
      {
        closed = false;
      }
    }
    return closed;
  }

}
