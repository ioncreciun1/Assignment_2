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

  }
  public void openChat() throws IOException
  {

    boolean pass = viewModel.verifyPass(password.getText(),username.getText());
    if(!pass)
    {
      error.setText("Wrong password");
    }
    else if(password.getText().equals(""))
    {
      error.setText("Insert Password");
    }
    else {
      error.setText("Oky Talky");
    }
    if(pass)
      viewHandler.openView("chat");
    }
}
