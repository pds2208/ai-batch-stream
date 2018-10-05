package uk.gov.ons.batch;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;
import uk.gov.ons.batch.model.AddressRequestMessage;
import uk.gov.ons.batch.model.SFTPRequestMessage;

public class SFTPMessageConverter extends AbstractMessageConverter {

    SFTPMessageConverter() {
        super(new MimeType("application", "octet-stream"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return (SFTPRequestMessage.class == clazz);
    }

    @Override
    protected AddressRequestMessage convertFromInternal(Message<?> message, Class<?> targetClass,
                                                        Object conversionHint) {
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        String fileName = (String) headers.get("file_originalFile");
        String text = payload instanceof String ? (String) payload : new String((byte[]) payload);
        return new SFTPRequestMessage(fileName, text);
    }
}
