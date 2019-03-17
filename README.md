# restfull-service-app

Spring Boot 版本号:`2.0.5.RELEASE`

## 遇到的坑

### Java8 的时间序列化和反序列化
Spring Boot 默认 Java8的序列化格式："yyyy-MM-dd'T'HH:mm:ss";

自定义格式为"yyyy-MM-dd HH:mm:ss",在 `Jackson2ObjectMapper` 实例化后修改java8时间格式 

```java
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
```

### H2 数据库产生的文件-项目根目录

h2 URL设置`url: jdbc:h2:./h2test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`



