package com.wrh.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wuruohong
 * @Classname DateFormatConfi
 * @Description 全局日期格式化
 * @Date 2020/10/20 11:15
 */
@JsonComponent
public class DateFormatConfig {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class DateJsonSerializer extends JsonSerializer<LocalDateTime> {

        /**
         * 日期格式化
         * @param date
         * @param jsonGenerator
         * @param serializerProvider
         * @throws IOException
         * @throws JsonProcessingException
         */
        @Override
        public void serialize(LocalDateTime date, org.apache.htrace.fasterxml.jackson.core.JsonGenerator jsonGenerator, org.apache.htrace.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(dateTimeFormatter.format(date));
        }

        public static class DateJsonDeserializer extends JsonDeserializer<LocalDateTime> {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
                return LocalDateTime.parse(jsonParser.getText(), dateTimeFormatter);
            }
        }
    }
}
