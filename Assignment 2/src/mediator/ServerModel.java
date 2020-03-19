package mediator;

import java.io.IOException;

public interface ServerModel
{
  public void connect() throws IOException;
   public void disconnect() throws IOException;
  //public String convert(String source) throws IOException;
  public void verifyLog(String username,String password);
}
