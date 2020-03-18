package view;

import javafx.scene.layout.Region;
import viewModel.LoginViewModel;

public class LoginViewController
{
  private ViewHandler viewHandler;
  private LoginViewModel viewModel;
  private Region root;
  public void reset()
  {
  }

  public Region getRoot()
  {
    return  root;
  }

  public void init(ViewHandler viewHandler, LoginViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }
  public void openChat()
  {
    viewHandler.openView("chat");
  }
}
