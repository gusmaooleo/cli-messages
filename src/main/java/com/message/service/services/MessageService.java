package com.message.service.services;

import com.message.service.config.PusherClient;
import com.message.service.config.PusherRest;
import com.pusher.client.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
  @Autowired
  private PusherClient client;

  @Autowired
  private PusherRest rest;

  private Channel channel;

  public MessageService(PusherRest rest, PusherClient client, String channelName) {
    this.rest = rest;
    this.client = client;
    this.channel = client.getChannel(channelName);
  }

  @Autowired
  public MessageService(PusherRest rest, PusherClient client) {
    this.rest = rest;
    this.client = client;
    this.channel = client.getChannel();
  }

  public void initializeChannel(String event) {
    this.client.connect();
    channel.bind(event, pusherEvent -> System.out.println(pusherEvent.getData().replaceAll("^\"|\"$", "")));
  }

  public void sendMessage(String message, String eventName, String username) {
    rest.sendMessage(message, eventName, username);
  }
}
