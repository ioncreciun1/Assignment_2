package mediator;

import java.io.IOException;

public interface ServerModel
{
   void connect() throws IOException;
    void disconnect() throws IOException;
   void verifyLog(String password);
}
