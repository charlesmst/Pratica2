package br.com.empresa.rh.resources;

import br.com.empresa.rh.filter.CORSFilter;
import br.com.empresa.rh.filter.SecurityFilter;
import br.com.empresa.rh.model.Menu;
import br.com.empresa.rh.service.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@ComponentScan("br.com.empresa.rh.resources")
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class JAXRSServerConfig {

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public Server jaxRsServer() {
        List<ResourceProvider> resourceProviders = new LinkedList<ResourceProvider>();
        for (String beanName : ctx.getBeanDefinitionNames()) {
            if (ctx.findAnnotationOnBean(beanName, Path.class) != null) {
                SpringResourceFactory resourceFactory = new SpringResourceFactory(beanName);
                resourceFactory.setApplicationContext(ctx);
                resourceProviders.add(resourceFactory);
            }
        }

        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setBus(ctx.getBean(SpringBus.class));
        factory.setResourceProviders(resourceProviders);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
//        MappingJackson2HttpMessageConverter jacksonJsonProvider = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(df);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //JaxbAnnotationModule jaxbModule = new JaxbAnnotationModule();
        //objectMapper.registerModule(jaxbModule);
        objectMapper.registerModule(new Hibernate4Module());
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jacksonJsonProvider.setMapper(objectMapper);

        factory.setProvider(jacksonJsonProvider);
        factory.setProvider(new CORSFilter());
        factory.setProvider(new SecurityFilter());

        Server s = null;
        s = factory.create();

        return s;
    }

}
