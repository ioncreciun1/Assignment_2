package mediator;

import com.google.gson.Gson;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReciver implements Runnable
{
  private BufferedReader in;
  private LoginClient client;

  public ClientReciver(LoginClient client, BufferedReader in)
  {
    this.client = client;
    this.in = in;
  }

  @Override public void run()
  {
    Gson gson = new Gson();

    while (true)
    {

      try
      {
        String reply = in.readLine();
        Message message = gson.fromJson(reply, Message.class);
        if ("chatters".equalsIgnoreCase(message.getBody()))
        {

          System.out.println(message.getBody() + " HERE IT IS");
        }
        else{
          client.setMessage(message.getUser() + " : " + message.getBody());
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
