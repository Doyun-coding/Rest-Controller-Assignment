package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.Project;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvHttpListConverter extends AbstractHttpMessageConverter<Object> {
    public CsvHttpListConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (!(o instanceof List<?> list)) {
            return;
        }

        if (list.get(0) instanceof Member) {
            List<Member> members = (List<Member>) o;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id,name,age,class\n");

            for (Member member : members) {
                stringBuilder.append(member.getId()).append(",")
                        .append(member.getName()).append(",")
                        .append(member.getAge()).append(",")
                        .append(member.getClazz().name()).append("\n");
            }

            OutputStream outputStream = outputMessage.getBody();
            outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            return;
        }

        if(list.get(0) instanceof Project) {
            List<Project> projects = (List<Project>) o;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("code,localDate,type\n");

            for (Project project : projects) {
                stringBuilder.append(project.getCode()).append(",")
                        .append(project.getLocalDate()).append(",")
                        .append(project.getType().name()).append("\n");
            }

            OutputStream outputStream = outputMessage.getBody();
            outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            return;
        }


    }
}
