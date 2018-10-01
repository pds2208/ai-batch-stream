package uk.gov.ons.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import uk.gov.ons.batch.model.FileMessage;

/**
 *
 */
@SpringBootApplication
@EnableBinding(Processor.class)
public class FileConverter {

    public static void main(String[] args) {
        SpringApplication.run(FileConverter.class, args);
    }

    @Bean
    @StreamMessageConverter
    public MessageConverter providesCSVMessageConverter() {
        return new FileMessageConverter();
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String processCSVtoJSON(FileMessage log) {
        String s = log.getasJSON();
        System.err.printf("******* File name: %s, JSON: %s\n", log.getCallback(),
                log.getasJSON());
        return s;
    }
}
