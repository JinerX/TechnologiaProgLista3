package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;


public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {

  @Test
  public void testChainWordCensorAndURLAppender() {
    // Create the builder
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

    // create a spy social channel
    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

    SocialChannel channel = builder.with(new WordCensor()).with(new URLAppender("http://example.com")).getDecoratedChannel(props);

    channel.deliverMessage("Microsoft unga bunga random noises");
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);

    assertEquals("### unga bunga random noises http://example.com", spyChannel.lastMessagePublished());
  }

  @Test
  public void testChainWordCensorAndMessageTruncator() {
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
    SocialChannel channel = builder.with(new WordCensor()).with(new MessageTruncator(20)).getDecoratedChannel(props);

    channel.deliverMessage("Microsoft is falling behind Apple in the market");
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
    assertTrue(spyChannel.lastMessagePublished().startsWith("### is fall"));
  }

  @Test
  public void testAllDecorators() {
    SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
    SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
    SocialChannel channel = builder.with(new WordCensor()).with(new MessageTruncator(30)).with(new URLAppender("http://ex.com")).getDecoratedChannel(props);

    channel.deliverMessage("Microsoft Electronics");
    TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
    assertTrue(spyChannel.lastMessagePublished().startsWith("### Electronics http://"));
  }
}