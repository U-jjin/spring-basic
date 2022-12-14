package com.springstarter.springbasicv2.scan.filter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {


    @Test
    public void filterScan(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA a = ac.getBean("beanA",BeanA.class);
        assertThat(a).isNotNull();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            ,excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes= MyExcludeComponent.class))
    static class ComponentFilterAppConfig{

    }
}
