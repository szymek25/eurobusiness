package pl.eurobusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import pl.eurobusiness.service.MessageService;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class DefaultMessageServiceService implements MessageService {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    @Override
    public String get(String code) {
        return  accessor.getMessage(code);
    }
}
