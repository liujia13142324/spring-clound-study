package com.lj.springcloud.study.eurekaserver.config;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.EurekaEvent;
import com.netflix.discovery.EurekaEventListener;
import com.netflix.discovery.StatusChangeEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.cloud.configuration.SSLContextFactory;
import org.springframework.cloud.configuration.TlsProperties;
import org.springframework.cloud.netflix.eureka.MutableDiscoveryClientOptionalArgs;
import org.springframework.cloud.netflix.eureka.config.DiscoveryClientOptionalArgsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class EurakeConfig {
  @Bean
  @ConditionalOnClass(name = "com.sun.jersey.api.client.filter.ClientFilter")
  public MutableDiscoveryClientOptionalArgs discoveryClientOptionalArgs(
          TlsProperties tlsProperties) throws GeneralSecurityException, IOException {
    MutableDiscoveryClientOptionalArgs result = new MutableDiscoveryClientOptionalArgs();
    Set<EurekaEventListener> listeners = new HashSet<>();
    listeners.add(event -> {
      if (event instanceof StatusChangeEvent) {
        StatusChangeEvent e2 = (StatusChangeEvent) event;
        System.out.println(e2.getStatus());
        System.out.println(e2.getPreviousStatus());
      }
    });
    
    result.setEventListeners( listeners );
    setupTLS(result, tlsProperties);
    return result;
  }
  
  private void setupTLS(AbstractDiscoveryClientOptionalArgs<?> args,
                               TlsProperties properties) throws GeneralSecurityException, IOException {
    if (properties.isEnabled()) {
      SSLContextFactory factory = new SSLContextFactory(properties);
      args.setSSLContext(factory.createSSLContext());
    }
  }
 
 /* @Bean
  public Set<EurekaEventListener> statusChange(){
    Set<EurekaEventListener> listeners = new HashSet<>();
    listeners.add(new EurekaEventListener() {
      @Override
      public void onEvent(EurekaEvent event) {
        if (event instanceof StatusChangeEvent) {
          StatusChangeEvent e2 = (StatusChangeEvent) event;
          System.out.println(e2.getStatus());
          System.out.println(e2.getPreviousStatus());
        }
      }
    });
    return listeners;
  }*/
  
}
