package view;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import viewModel.LoginViewModel;

import java.io.IOException;

public class LoginViewController
{
  public TextField username;
  public PasswordField password;
  public Label error;
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
    password.textProperty().bindBidirectional(viewModel.passwordProperty());

  }
  public void openChat() throws IOException
  {


    if(password.getText().equals(""))
    {
      error.setText("Insert Password");
    }
    if(viewModel.verifyPass())
      viewHandler.openView("chat");
    }

  public void verify(KeyEvent keyEvent) throws IOException
  {

    System.out.println(viewModel.verifyPass());
    if(!viewModel.verifyPass())
    {
      error.setText("Wrong password");
    }
    else {
      error.setText("Oki Talky");
    }
  }
}
