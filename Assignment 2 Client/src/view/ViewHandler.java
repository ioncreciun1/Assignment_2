package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private LoginViewController logViewController;
  private ChatViewController chatViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("login");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {

      case "login":
        root = loadLogView("LOGIN-As2.fxml");
        break;
      case "chat":
        root = loadChatView("Chat-OkyToky-As2.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(400);
    primaryStage.setHeight(560);
    primaryStage.show();
  }


  private Region loadLogView(String fxmlFile)
{
  if (logViewController == null)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      Region root = loader.load();
      logViewController = loader.getController();
      logViewController.init(this, viewModelFactory.getLogViewModel(), root);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  else
  {
    logViewController.reset();
  }
  return logViewController.getRoot();
}
  private Region loadChatView(String fxmlFile)
  {
    if (chatViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatViewController = loader.getController();
        chatViewController.init(this, viewModelFactory.getChatViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatViewController.reset();
    }
    return chatViewController.getRoot();
  }

}
