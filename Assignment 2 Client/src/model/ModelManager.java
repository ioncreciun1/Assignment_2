package model;

import mediator.LoginClient;
import mediator.ServerModel;
import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model
{
  private ServerModel serverModel;
  private PropertyChangeSupport property;

  public ModelManager() throws IOException
  {
    this.serverModel = new LoginClient(this);
    serverModel.connect();
    property = new PropertyChangeSupport(this);
  }

  @Override public void setMessage(String message) throws IOException
  {
    property.firePropertyChange("message", null, message);
  }

  @Override public void sendMessage(String message) throws IOException
  {
    serverModel.sendMessage(message);
  }

  @Override public boolean verifyLog(String password,String name) throws IOException
  {

    return serverModel.verifyLog(password,name);
  }
  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName,listener);
  }
}
