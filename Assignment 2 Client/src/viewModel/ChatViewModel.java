package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ChatViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<String> logs;
  public ChatViewModel(Model model)
  {
    this.model = model;
    logs = FXCollections.observableArrayList();
    model.addListener("message",this);
  }
  public ObservableList<String> getLogs()
  {
    return logs;
  }
  public void setMessage(String inputField) throws IOException
  {

    model.sendMessage(inputField);
  }
  public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      logs.add(0, evt.getNewValue() + "");
    });
  }

}
