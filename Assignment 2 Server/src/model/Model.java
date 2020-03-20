package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  void addLog(String log);
  boolean verifyLog(String request);
}
