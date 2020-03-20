package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
  private ServerSocket welcomeSocket;
  public ChatServer(int Port) throws IOException
  {
    this.welcomeSocket = new ServerSocket(Port);
  }
  public void execute() throws IOException
  {
    while(true)
    {

      try
      {
        Socket socket = welcomeSocket.accept();
        CommunicationThreadHandler handler = new CommunicationThreadHandler(socket);
        Thread t = new Thread(handler);
        t.start();
      }catch(Exception e)
      {
        System.out.println(e.getMessage());
      }

    }
  }
}
