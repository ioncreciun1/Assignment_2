package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String id;
  private String messageBody;
  private LocalDateTime dateTime;
  private String user;

  public Message(String id, String message,String user)
  {
    this.dateTime = LocalDateTime.now();
    this.id = id;
    this.messageBody = message;
    this.user = user;
  }

  public Message(String message,String user)
  {
    this("0", message,user);
    setId("" + (int) (messageBody.hashCode() * Math.random()));
  }

  public Message update()
  {
    this.dateTime = LocalDateTime.now();
    return this;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getBody()
  {
    return messageBody;
  }
  public String getUser()
  {
    return user;
  }

  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  public String getDateTime(String format)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return dateTime.format(formatter);
  }

  public String toString()
  {
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("d/MM/yyyy HH:mm:ss");
    return "id=" + id + ", time=" + dateTime.format(formatter) + ", message=\""
        + messageBody + "\"";
  }
}