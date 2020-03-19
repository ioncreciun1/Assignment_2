package server;

import com.google.gson.Gson;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInClientHandler extends Thread
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Model model;

  public LogInClientHandler(Socket socket, Model model,ThreadGroup group) throws IOException
  {
    super(group, "ClientHandlerThreads");
    this.running = false;
    this.socket = socket;
    this.model = model;
    in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    out = new PrintWriter(this.socket.getOutputStream(), true);
  }

  @Override public void run()
  {
    running = true;
    while (running)
    {
      try
      {
        String request = in.readLine();
        String name = in.readLine();
        if(model.verifyLog(request))
        {
          model.addLog("Client " + name + " Connected");
          out.println("approved");
        }
        else
        {
          model.addLog("Client" + name + " did not connect");
          out.println("denied");
        }
      }
      catch (IOException e)
      {
        close();
      }
    }
    close();
  }
  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }
}
