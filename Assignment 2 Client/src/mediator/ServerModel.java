package mediator;

import java.io.IOException;

public interface ServerModel
{
    void disconnect() throws IOException;
    void connect() throws IOException;
  boolean verifyLog(String password,String name) throws IOException;
  void setMessage(String message) throws IOException;
  void sendMessage(String message) throws IOException;
}
