package view;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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

  public void back()
  {
    viewHandler.openView("login");
  }

  public void setText(ActionEvent event) throws IOException
  {
    viewModel.setMessage(text.getText());
    text.setText("");
  }
}
