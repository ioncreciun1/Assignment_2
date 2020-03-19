package mediator;

import java.io.IOException;

public interface ServerModel
{
   void connect() throws IOException;
    void disconnect() throws IOException;
  boolean verifyLog(String password) throws IOException;
}
