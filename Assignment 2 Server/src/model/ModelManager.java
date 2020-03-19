package model;

import server.LogInClientConnector;
import server.LogInClientHandler;

import java.io.IOException;

public class ModelManager implements Model
{
  private LogInClientHandler logInClientHandler;

  public ModelManager()
  {

  }
  @Override public boolean verifyLog(String password) throws IOException
  {
    return password.equals("1234567");
  }
}
