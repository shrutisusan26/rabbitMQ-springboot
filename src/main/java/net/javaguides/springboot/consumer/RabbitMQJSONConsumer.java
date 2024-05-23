package net.javaguides.springboot.consumer;

import net.javaguides.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);


    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJSONMessage(User user){
        LOGGER.info(String.format("Received JSON Message -> %s" , user.toString()));
        return;
    }

}
