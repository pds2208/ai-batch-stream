package uk.gov.ons.batch;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;
import uk.gov.ons.batch.model.FileMessage;

public class FileMessageConverter extends AbstractMessageConverter {

    FileMessageConverter() {
        super(new MimeType("application", "octet-stream"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return (FileMessage.class == clazz);
    }

    @Override
    protected FileMessage convertFromInternal(Message<?> message, Class<?> targetClass,
                                              Object conversionHint) {
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        String fileName = (String) headers.get("file_originalFile");
        if (fileName != null) {
            // we are dealing with a file
            // find archive directory and move file there
        }
        String text = payload instanceof String ? (String) payload : new String((byte[]) payload);
        return new FileMessage(fileName, text);
    }
}
