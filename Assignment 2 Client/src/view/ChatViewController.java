package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ChatViewModel;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ChatViewController
{
  public ListView<String> list;
  public TextField text;
  private ViewHandler viewHandler;
  private Region root;
  private ChatViewModel viewModel;

  public void init(ViewHandler viewHandler, ChatViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    list.setItems(viewModel.getLogs());
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return  root;
  }


  public void setText(ActionEvent event) throws IOException
  {

    if(!text.getText().equals(""))
    {
      viewModel.setMessage(text.getText());
      if(text.getText().equalsIgnoreCase("close"))
      {
        Stage stage = (Stage)text.getScene().getWindow();
        System.out.println(stage);
        stage.close();

      }
      text.setText("");
    }
  }
}
