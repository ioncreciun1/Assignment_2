package mediator;

import com.google.gson.Gson;


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

        System.out.println("REPLY : " + reply);
        if ("list".equalsIgnoreCase(reply))
        {

        }
        else{
          client.setMessage(reply);
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
