package hello.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatterBuilder;


@Configuration
public class AppWebMvcConfig {

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @PostConstruct
    public void postConstruct() {
        this.jackson2ObjectMapperBuilder.serializers(new LocalDateSerializer(new DateTimeFormatterBuilder()
                .appendPattern("uuuu-MM-dd").toFormatter()));
        this.jackson2ObjectMapperBuilder.deserializers(new LocalDateDeserializer(new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd").toFormatter()));
        this.jackson2ObjectMapperBuilder.deserializers(new LocalDateTimeDeserializer(new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd HH:mm:ss").toFormatter()));
        this.jackson2ObjectMapperBuilder.serializers(new LocalDateTimeSerializer(new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd HH:mm:ss").toFormatter()));
    }
}
