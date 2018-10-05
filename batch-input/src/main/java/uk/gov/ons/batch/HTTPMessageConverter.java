package uk.gov.ons.batch;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;
import uk.gov.ons.batch.model.AddressRequestMessage;
import uk.gov.ons.batch.model.HTTPRequestMessage;

public class HTTPMessageConverter extends AbstractMessageConverter {

    HTTPMessageConverter() {
        super(new MimeType("application", "json"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return (HTTPRequestMessage.class == clazz);
    }

    @Override
    protected AddressRequestMessage convertFromInternal(Message<?> message, Class<?> targetClass,
                                                        Object conversionHint) {
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        String cu = (String) headers.get("callback-url");
        String text = payload instanceof String ? (String) payload : new String((byte[]) payload);
        return new HTTPRequestMessage(cu, text);
    }
}
