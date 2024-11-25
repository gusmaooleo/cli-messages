package com.message.service.config;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PusherClient {
  private final String defaultChannelName;
  final private Pusher pusher;

  @Autowired
  public PusherClient(
          @Value("${env.key}") String key,
          @Value("${env.cluster}") String cluster,
          @Value("${env.channel_name}") String defaultChannelName
  ) {
    PusherOptions options = new PusherOptions().setCluster(cluster);
    this.pusher = new Pusher(key, options);
    this.defaultChannelName = defaultChannelName;
  }

  public Channel getChannel(String newChannelName) {
    return pusher.subscribe(newChannelName);
  }

  public Channel getChannel() {
    return pusher.subscribe(defaultChannelName);
  }

  public void connect() {
    pusher.connect(new ConnectionEventListener() {
      @Override
      public void onConnectionStateChange(ConnectionStateChange connectionStateChange) {
        System.out.println("State changed to " + connectionStateChange.getCurrentState() +
                " from " + connectionStateChange.getPreviousState());
      }

      @Override
      public void onError(String s, String s1, Exception e) {
        System.out.println("System connection error");
      }
    }, ConnectionState.ALL);
  }

  public void disconnect() {
    pusher.disconnect();
  }
}
