package com.kstrinadka.workerproject.net;

import com.rabbitmq.client.Channel;

import java.util.List;

public interface ManagerSender
{
    void send(List<String> result, String ticketID, Channel channel, long tag);
}
