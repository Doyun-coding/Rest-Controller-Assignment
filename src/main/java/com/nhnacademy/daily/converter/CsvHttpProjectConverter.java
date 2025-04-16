package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.ClassType;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.Project;
import com.nhnacademy.daily.model.ProjectType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CsvHttpProjectConverter extends AbstractHttpMessageConverter<Project> {
    public CsvHttpProjectConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    protected Project readInternal(Class<? extends Project> clazz,
                                   HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line = bufferedReader.readLine();

            String[] tokens = line.split(",");

            String code = tokens[0];
            String localDate = tokens[1];
            String type = tokens[2];

            return new Project(code, LocalDate.parse(localDate, DateTimeFormatter.ofPattern("yyyyMMdd")), ProjectType.valueOf(type));

        }
    }

    @Override
    protected void writeInternal(Project project,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("code,localDate,type").append("\n");

        stringBuilder.append(project.getCode()).append(",");
        stringBuilder.append(project.getLocalDate()).append(",");
        stringBuilder.append(project.getType().name()).append("\n");

        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

    }
}
