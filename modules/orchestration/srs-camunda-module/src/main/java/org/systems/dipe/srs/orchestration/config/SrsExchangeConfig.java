package org.systems.dipe.srs.orchestration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplateBuilder;

public class SrsExchangeConfig {

    public static final String SRS_EXCHANGE = "srs-exchange";
    public static final String SRS_QUEUE_PREFIX = "srs-";
    public static final String SRS_QUEUE_POSTFIX = "-queue";
    public static final String SRS_DL_QUEUE_POSTFIX = "-dlq";

    public static final String SRS_CAMUNDA_ROUTING_KEY = "camunda";
    public static final String SRS_CAMUNDA_QUEUE = SRS_QUEUE_PREFIX + "camunda" + SRS_QUEUE_POSTFIX;
    public static final String SRS_CAMUNDA_DL_ROUTING_KEY = "camunda-dlq";
    public static final String SRS_CAMUNDA_DL_QUEUE = SRS_QUEUE_PREFIX + "camunda" + SRS_DL_QUEUE_POSTFIX;

    @Bean
    public Jackson2JsonMessageConverter jackson2Converter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setRetryTemplate(new RetryTemplateBuilder()
                .maxAttempts(3)
                .fixedBackoff(5_000)
                .build());
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setMessageConverter(messageConverter);
        return containerFactory;
    }

    // ------------------------ Exchange and Queues ------------------------------------- //

    @Bean(SRS_EXCHANGE)
    public TopicExchange camundaExchange() {
        return new TopicExchange(SRS_EXCHANGE);
    }

    @Bean(SRS_CAMUNDA_QUEUE)
    public Queue camundaQueue() {
        return QueueBuilder.durable(SRS_CAMUNDA_QUEUE)
                .deadLetterExchange(SRS_EXCHANGE)
                .deadLetterRoutingKey(SRS_CAMUNDA_DL_ROUTING_KEY)
                .build();
    }

    @Bean
    public Binding camundaBinding(
            @Qualifier(SRS_CAMUNDA_QUEUE) Queue queue,
            @Qualifier(SRS_EXCHANGE) TopicExchange exchange
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(SRS_CAMUNDA_ROUTING_KEY);
    }

    @Bean(SRS_CAMUNDA_DL_QUEUE)
    public Queue camundaDeadLetterQueue() {
        return QueueBuilder.durable(SRS_CAMUNDA_DL_QUEUE).build();
    }

    @Bean
    public Binding camundaDeadLetterBinding(
            @Qualifier(SRS_CAMUNDA_DL_QUEUE) Queue queue,
            @Qualifier(SRS_EXCHANGE) TopicExchange exchange
    ) {
        return BindingBuilder.bind(queue).to(exchange).with(SRS_CAMUNDA_DL_ROUTING_KEY);
    }
}
