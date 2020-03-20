package model;

import java.util.ArrayList;

public class Converter
{
  private ArrayList<String> list;
  
  public Converter()
  {
    this.list = new ArrayList<>();
  }
  
  public void addLog(String txt)
  {
    list.add(txt);
  }

  public int getLogSize()
  {
    return list.size();
  }
  

}
