package com.br.ms_save_payments.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcucerEvent {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String topicName = "kafka_payments_topic";

    public void sendMessage(Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topicName, jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter mensagem para JSON", e);
        }
    }
}
