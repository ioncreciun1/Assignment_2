package server;

import com.google.gson.Gson;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThreadHandler implements Runnable
{
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private String ip;
  public CommunicationThreadHandler(Socket socket) throws IOException
  {
    this.socket = socket;
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(),true);
  }

  @Override public void run()
  {
    Gson gson = new Gson();
    while(true)
    {
      try
      {
        String json = in.readLine();
        System.out.println("MESSAGE : " + json);;
        Message message = gson.fromJson(json, Message.class);
        System.out.println(message);
        out.println(message);
        if(message.toString().equals("Exit"))
        {
          break;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
