package net.javaguides.springboot.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue.json.name}")
    private String queue_json;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.routingKey.json}")
    private String routingJSONKey;


    // spring bean for RabbitMQ queue
    @Bean
    public Queue queue(){
        return  new Queue(queueName);
    }
    // spring bean for queue = to store json messages

    @Bean
    public Queue jsonQueue(){
        return new Queue(queue_json);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    // binding between queue and exchange
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    };

    @Bean
    public Binding bindingwithJSON(){
        return  BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJSONKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    };

    // Spring boot autoconfiguration will automatically create beans for the following:

    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin

}
