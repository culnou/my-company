package com.culnou.mumu;

import javax.jms.ConnectionFactory;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@SpringBootApplication
@EnableJms
public class MumuTest20201009Application {
	
	//mumuFactoryの定義
	//JmsMessageListenerContainer は、Boot がデフォルトで作成するものと同じ。
	
	@Bean
	public JmsListenerContainerFactory<?> mumuFactory(ConnectionFactory connectionFactory,
	                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    // This provides all boot's default to this factory, including the message converter
	    configurer.configure(factory, connectionFactory);
	    // You could still override some of Boot's default if necessary.
	    //トランザクション管理の追加
	    //factory.setTransactionManager(transactionManager);
	    //factory.setTransactionManager(null);
	    //factory.setSessionTransacted(false);
	    return factory;
	  }
	  
     
	 @Bean // Serialize message content to json using TextMessage
	 public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	 }
	 
	 /*
	 @Bean
	  public TopicConnectionFactory jmsConnectionFactory() {
		  //ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		  ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://160.16.113.131:61616");
	      return connectionFactory;
	  }
	  
	  
	  @Bean
	  public JmsTransactionManager jmsTransactionManager() {
	      JmsTransactionManager transactionManager = new JmsTransactionManager();
	      transactionManager.setConnectionFactory(jmsConnectionFactory());
	      return transactionManager;
	  }
	  */
	  
	  
	  
	  
	  /*
	  @Bean
	  public ChainedTransactionManager transactionManager() {
		  ChainedTransactionManager transactionManager = new ChainedTransactionManager();
	      //transactionManager.s
	      return transactionManager;
	  }
	  */
	  
	  

	public static void main(String[] args) {
		SpringApplication.run(MumuTest20201009Application.class, args);
	}

}
