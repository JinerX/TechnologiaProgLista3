package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class WordCensorTest extends AbstractSocialChanneldDecoratorTest {

  //No "Microsoft"
  @Test
  public void testWordCensorNoCensoring() {
    // Create the builder
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

    // create a spy social channel
    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
    SocialChannel channel = builder.with(new WordCensor()).getDecoratedChannel(props);

    // Now call channel.deliverMessage
    channel.deliverMessage("this is a message");

    // Spy channel. Should get the one created before
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
    assertEquals("this is a message", spyChannel.lastMessagePublished());
  }

  //w/Microsoft
  @Test
  public void testWordCensorCensoring() {
    // Create the builder
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

    // create a spy social channel
    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
    SocialChannel channel = builder.with(new WordCensor()).getDecoratedChannel(props);

    // Now call channel.deliverMessage
    channel.deliverMessage("this is a Microsoft message");

    // Spy channel. Should get the one created before
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
    assertEquals("this is a ### message", spyChannel.lastMessagePublished());
  }

  //w/Microsoft Case Insensitive
  @Test
  public void testWordCensorCensoringCaseInsensitive() {
    // Create the builder
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

    // create a spy social channel
    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
    SocialChannel channel = builder.with(new WordCensor()).getDecoratedChannel(props);

    // Now call channel.deliverMessage
    channel.deliverMessage("this is a miCroSoFt message");

    // Spy channel. Should get the one created before
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
    assertEquals("this is a ### message", spyChannel.lastMessagePublished());
  }
}
