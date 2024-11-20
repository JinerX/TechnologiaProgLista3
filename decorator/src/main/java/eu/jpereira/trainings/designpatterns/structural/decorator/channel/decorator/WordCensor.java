package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCensor extends SocialChannelDecorator{
  private String censoredWord;

  public WordCensor() {
    this.censoredWord = "Microsoft";
  }

  //decoratedChannel - what channel will this decorator be applied to
  public WordCensor(SocialChannel decoratedChannel) {
    this.censoredWord = "Microsoft";
    this.delegate = decoratedChannel;
  }

  //deliverMessage function - the logic of substituting the Censored word to "###"
  @Override
  public void deliverMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    Pattern pattern = Pattern.compile("\\bMicrosoft\\b", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(message);

    message = matcher.replaceAll("###");

    if (delegate != null) {
      delegate.deliverMessage(message);
    }
  }

}
