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
import uk.gov.ons.batch.model.AddressRequestMessage;
import uk.gov.ons.batch.model.SFTPRequestMessage;

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
    public MessageConverter sftpInputMessageConverter() {
        return new SFTPMessageConverter();
    }

    @Bean
    @StreamMessageConverter
    public MessageConverter httpInputMessageConverter() {
        return new HTTPMessageConverter();
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String processRequest(AddressRequestMessage log) {

        if (log.getSource() == AddressRequestMessage.SOURCE.SFTP_SOURCE) {
            String s = ((SFTPRequestMessage) log).getasJSON();
            System.err.printf("******* File name: %s, JSON: %s\n",
                    log.getRequestSource(), s);
            return s;
        } else {
            // HTTP is already using JSON format
            System.err.printf("******* HTTP callback: %s, JSON: %s\n",
                    log.getRequestSource(), log.getMessage());
            return log.getMessage();
        }
    }
}
