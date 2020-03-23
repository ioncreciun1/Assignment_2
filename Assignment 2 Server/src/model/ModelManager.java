package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private Converter converter;
  private PropertyChangeSupport property;
  private ArrayList<String> list = new ArrayList<>();

  public ModelManager()
  {
    this.converter = new Converter();
    property = new PropertyChangeSupport(this);
  }
  @Override
  public synchronized void addLog(String log)
  {
    String logValue = converter.getLogSize() + ": " + log;
    converter.addLog(logValue);
    property.firePropertyChange("add", null, log);
  }
  public void addUser(String user)
  {
    list.add(user);
  }

  @Override public void removeUser(String user)
  {
    list.remove(user);
  }

  public String getList()
  {
    String s ="";
    for(int i=0;i<list.size()-1;i++){
      s+=list.get(i) + " , ";
    }
    s+=list.get(list.size()-1);
    return s;
  }

  @Override public boolean verifyLog(String request)
  {
    return  request.equals("123456");
  }

  @Override public void addMessage(String message,String user)
  {
    property.firePropertyChange("user", null, user);
    property.firePropertyChange("message", null, message);

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
