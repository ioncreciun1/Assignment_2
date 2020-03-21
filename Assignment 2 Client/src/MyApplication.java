import javafx.application.Application;
    import javafx.stage.Stage;
import mediator.LoginClient;
import mediator.ServerModel;
import model.Model;
    import model.ModelManager;
    import view.ViewHandler;
    import viewModel.ViewModelFactory;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);
  }
}
