package server;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Message;
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
  private String user;
  private String users ="";
  private Gson gson;

  public LogInClientHandler(Socket socket, Model model) throws IOException
  {
    gson = new Gson();
    this.running = false;
    this.socket = socket;
    this.model = model;
    in = new BufferedReader(
        new InputStreamReader(this.socket.getInputStream()));
    this.model.addListener("message", this);
    this.model.addListener("user", this);
    out = new PrintWriter(this.socket.getOutputStream(), true);
  }

  @Override public void run()
  {
    running = true;
    while (running)
    {

      try{
       String request = in.readLine();
     //  System.out.println(request);
       String name = in.readLine();
        //System.out.println(name);

       this.user = name;

      if (model.verifyLog(request))
      {

        System.out.println(users);
        model.addLog("Client " + name + " Connected");
        out.println("approved");
        running = false;
      }
      else
      {
        //System.out.println("Here");
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
        Message request = gson.fromJson(message,Message.class);
        System.out.println(request.getBody().equals("chatters"));
        if(request.getBody().equals("chatters"))
        {
          System.out.println("I am here");
          String json  = gson.toJson(request);
          out.println(json);
        }
        model.addMessage(request.getBody(),request.getUser());
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

     // System.out.println(evt.getPropertyName() + " THIS IS HERE");
      switch (evt.getPropertyName())
      {
        case "user" :
          //System.out.println("I am here");
          this.user = evt.getNewValue().toString(); break;
        case "message":
         // System.out.println(evt.getNewValue());
         // System.out.println(user);
          Message message = new Message("message",evt.getNewValue().toString(),user);
        //  System.out.println(message);

          String json = gson.toJson(message);
          out.println(json);
          break;

      }
    });
  }
}
