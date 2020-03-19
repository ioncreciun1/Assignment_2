package model;

import java.io.IOException;

public interface Model
{
  boolean verifyLog(String password) throws IOException;
}
