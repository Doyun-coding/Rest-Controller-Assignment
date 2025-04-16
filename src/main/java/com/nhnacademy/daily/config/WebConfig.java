package com.nhnacademy.daily.config;

import com.nhnacademy.daily.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvHttpMemberConverter());
        converters.add(new CsvHttpProjectConverter());
        converters.add(new CsvHttpListConverter());
        converters.add(new CsvHttpMemberCreateCommandConverter());
        converters.add(new CsvHttpProjectCreateCommandConverter());
    }

}
