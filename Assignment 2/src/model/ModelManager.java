package model;

public class ModelManager implements Model
{
  @Override public void verifyLog(String username, String password)
  {
    if(username.equals("") && password.equals("")){}
  }
}
