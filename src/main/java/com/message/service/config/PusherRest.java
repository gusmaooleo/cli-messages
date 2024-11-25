package com.message.service.config;

import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class PusherRest {
  final private String channelName;
  final private Pusher pusher;


  public PusherRest(@Value("${env.channel_name}") String channelName,
                    @Value("${env.app_id}") String app_id,
                    @Value("${env.key}") String key,
                    @Value("${env.secret}") String secret,
                    @Value("${env.cluster}") String cluster) {

    this.pusher = new Pusher(app_id, key, secret);
    this.pusher.setCluster(cluster);
    this.pusher.setEncrypted(true);
    this.channelName = channelName;
  }

  public void sendMessage(String message, String eventName, String username) {
    try {
      String buildMessage = username + ": " + message + "     " + new Date();
      pusher.trigger(channelName, eventName, buildMessage);
    } catch (Exception e) {
      System.out.println("error: " + e);
    }
  }

  public void sendMessage(String message, String eventName, String username, String customChannelName) {
    try {
      String buildMessage = username + ": " + message + "     " + new Date();
      pusher.trigger(customChannelName, eventName, buildMessage);
    } catch (Exception e) {
      System.out.println("error: " + e);
    }
  }
}
