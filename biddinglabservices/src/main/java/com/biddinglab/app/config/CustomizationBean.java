package com.biddinglab.app.config;



import org.springframework.boot.context.embedded.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.biddinglab.app.util.Url;

@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer {

    @SuppressWarnings("deprecation")
	@Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(9000);
        container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, Url.UNAUTHORIZED_PAGE));   
    }
}