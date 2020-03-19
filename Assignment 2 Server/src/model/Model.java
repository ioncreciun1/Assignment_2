package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  String convert(String source) throws Exception;
  void addLog(String log);
  boolean verifyLog(String request);
}
