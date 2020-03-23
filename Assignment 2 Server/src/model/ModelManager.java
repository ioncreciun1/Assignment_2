package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private Converter converter;
  private PropertyChangeSupport property;

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
    property.firePropertyChange("add", null, logValue);
  }

  @Override public boolean verifyLog(String request)
  {
    return  request.equals("111111");
  }

  @Override public void addMessage(String message)
  {
    System.out.println("HOW MANY TIMES");
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
