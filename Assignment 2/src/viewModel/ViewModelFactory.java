package viewModel;

import model.Model;

public class ViewModelFactory
{
  private LoginViewModel loginViewModel;
  private ChatViewModel chatViewModel;
  public ViewModelFactory(Model model)
  {
    this.loginViewModel = new LoginViewModel(model);
    this.chatViewModel = new ChatViewModel(model);
  }
  public LoginViewModel getLogViewModel()
  {
    return loginViewModel;
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }
}
