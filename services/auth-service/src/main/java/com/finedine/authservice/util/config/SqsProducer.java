package com.finedine.authservice.util.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Service for producing messages to AWS SQS queues.
 * This class uses SqsTemplate to send messages to the specified SQS queue.
 */

@Service
@RequiredArgsConstructor
public class SqsProducer {
    private final SqsTemplate sqsTemplate;
    private final ObjectMapper objectMapper;

    public void produce(String queueName, Object payload)
    {


        try {
            String jsonPayload= objectMapper.writeValueAsString(payload);
            this.sqsTemplate
                    .send(to->to.queue(queueName)
                            .payload(jsonPayload)
                    );
            System.out.println("sent queue"+":"+jsonPayload);

        } catch (JsonProcessingException e) {

            System.err.println("  failed to serialize payload to JSON:"+e.getMessage());
            throw new RuntimeException("Error sending message to SQS",e.getCause());
        }
    }

}
