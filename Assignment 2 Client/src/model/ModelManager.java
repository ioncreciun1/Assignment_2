package model;

import mediator.LoginClient;
import mediator.ServerModel;

import java.io.IOException;

public class ModelManager implements Model
{
  private ServerModel serverModel;
  public ModelManager()
  {

  }
  @Override public boolean verifyLog(String password) throws IOException
  {
    this.serverModel = new LoginClient();
    return serverModel.verifyLog(password);
  }
}
