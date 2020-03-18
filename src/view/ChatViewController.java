package view;

import javafx.scene.layout.Region;
import viewModel.ChatViewModel;
import viewModel.LoginViewModel;

public class ChatViewController
{
  private ViewHandler viewHandler;
  private Region root;
  private ChatViewModel viewModel;
  public void init(ViewHandler viewHandler, ChatViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return  root;
  }
}
