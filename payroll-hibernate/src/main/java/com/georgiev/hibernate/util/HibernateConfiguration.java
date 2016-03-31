package com.georgiev.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

  @Bean
  public SessionFactory buildSessionAnnotationFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
      configuration.configure("hibernate-annotation.cfg.xml");

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                                                                            .build();
      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      return sessionFactory;
    }
    catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  /*
   * @Bean
   * @Autowired public HibernateTransactionManager
   * transactionManager(SessionFactory s) { HibernateTransactionManager
   * txManager = new HibernateTransactionManager();
   * txManager.setSessionFactory(s); return txManager; }
   */}
