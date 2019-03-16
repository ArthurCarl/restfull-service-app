package hello.config;


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.PostConstruct;

import java.time.format.DateTimeFormatterBuilder;

@EnableWebSecurity
@Configuration
public class MvcConfig extends WebSecurityConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(MvcConfig.class);
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(new FastJsonHttpMessageConverter());
//    }

//    @Bean
//    public ObjectMapper objectMapper(){
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//
//        objectMapper.findAndRegisterModules();
//        System.out.println(objectMapper);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        logger.info("{}",objectMapper);
//
//        return objectMapper;
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
//            ObjectMapper objectMapper) {
//        System.out.println(objectMapper);
//        logger.info("{}",objectMapper);
//
//        MappingJackson2HttpMessageConverter a = new MappingJackson2HttpMessageConverter(objectMapper);
//        //a.serializers()
//        return a;
//    }

    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    @PostConstruct
    public void postConstruct() {
        this.builder.serializers(new LocalDateSerializer(new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd").toFormatter()));
        this.builder.deserializers(new LocalDateDeserializer(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter()));
        this.builder.deserializers(new LocalDateTimeDeserializer(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter()));
        this.builder.serializers(new LocalDateTimeSerializer(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter()));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // OPTIONS请求全部放行
                .antMatchers(HttpMethod.POST, "/authentication/**", "/h2-console/*").permitAll()  //登录和注册的接口放行，其他接口全部接受验证
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.PUT).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
                .antMatchers(HttpMethod.GET).authenticated();
        httpSecurity.headers().frameOptions().disable();

    }

//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/h2-console/*");
//        return registrationBean;
//    }
}