package server;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInClientHandler implements PropertyChangeListener, Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Model model;

  private Gson gson;

  public LogInClientHandler(Socket socket, Model model) throws IOException
  {
    this.running = false;
    this.socket = socket;
    this.model = model;
    in = new BufferedReader(
        new InputStreamReader(this.socket.getInputStream()));
    this.model.addListener("message", this);
    out = new PrintWriter(this.socket.getOutputStream(), true);
  }

  @Override public void run()
  {
    running = true;
    while (running)
    {

      try{
       String request = in.readLine();
       System.out.println(request);
       String name = in.readLine();
        System.out.println(socket);
        System.out.println(out.checkError());
      if (model.verifyLog(request))
      {
        System.out.println("I am here");
        model.addLog("Client " + name + " Connected");
        out.println("approved");
        running = false;
      }
      else
      {
        System.out.println("Here");
        model.addLog("Client " + name + " did not connect");
        out.println("denied");
      }
      int i = 0;
      }catch (IOException e)
      {
        e.getMessage();
      }

    }
    running = true;
    while (running)
    {
      try
      {
        String message = in.readLine();
        model.addLog("Message: " + message);

        model.addMessage(message);
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      System.out.println(evt.getPropertyName() + " THIS IS HERE");
      switch (evt.getPropertyName())
      {
        case "message":
          out.println(evt.getNewValue());
          break;
      }
    });
  }
}
