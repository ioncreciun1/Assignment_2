package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
  private String type;
  private String messageBody;
  private LocalDateTime dateTime;

  public Message(String type, String message)
  {
    this.dateTime = LocalDateTime.now();
    this.type = type;
    this.messageBody = message;
  }

  public Message update()
  {
    this.dateTime = LocalDateTime.now();
    return this;
  }

  public String getType()
  {
    return type;
  }

  public String getBody()
  {
    return messageBody;
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
    return "type=" + type + ", time=" + dateTime.format(formatter) + ", message=\""
        + messageBody + "\"";
  }
}