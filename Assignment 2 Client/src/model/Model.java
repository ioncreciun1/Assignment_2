package model;

import java.io.IOException;

public interface Model
{
  boolean verifyLog(String password,String name) throws IOException;
}
