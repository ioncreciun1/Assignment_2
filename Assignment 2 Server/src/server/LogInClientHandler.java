package server;

import com.google.gson.Gson;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Model model;

  public LogInClientHandler(Socket socket, Model model) throws IOException
  {
    this.running = false;
    this.socket = socket;
    this.model = model;
    in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    out = new PrintWriter(this.socket.getOutputStream(), true);
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        model.verifyLog(in.readLine());
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
