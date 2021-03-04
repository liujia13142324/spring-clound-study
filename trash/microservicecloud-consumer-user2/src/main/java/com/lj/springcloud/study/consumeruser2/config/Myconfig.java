package com.lj.springcloud.study.consumeruser2.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {

   /* @Bean
    public IRule loadBalanceMethod(){
        RetryRule retryRule = new RetryRule();
        retryRule.setMaxRetryMillis(5000);
        return retryRule;
    }*/
//   @RibbonClientName
   private String name = "client";

   @Bean
   public IRule loadBalanceMethod(){
       return new RoundRobinRule();
   }

   /* @Bean
    public IClientConfig ribbonClientConfig() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties(this.name);
        config.set(CommonClientConfigKey.ConnectTimeout, 1000);
        config.set(CommonClientConfigKey.ReadTimeout, 1000);
        config.set(CommonClientConfigKey.GZipPayload, true);
        config.set(CommonClientConfigKey.MaxAutoRetriesNextServer,0);
        return config;
    }*/
}
