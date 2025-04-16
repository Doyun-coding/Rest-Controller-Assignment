package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.ClassType;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberCreateCommand;
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

public class CsvHttpMemberConverter extends AbstractHttpMessageConverter<Member> {

    public CsvHttpMemberConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    protected Member readInternal(Class<? extends Member> clazz,
                                               HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line = bufferedReader.readLine();

            String[] tokens = line.split(",");

            String id = tokens[0];
            String name = tokens[1];
            String age = tokens[2];
            String clas = tokens[3];

            return new Member(id, name, Integer.parseInt(age), ClassType.valueOf(clas));
        }
    }

    @Override
    protected void writeInternal(Member member,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id,name,age,class").append("\n");

        stringBuilder.append(member.getId()).append(",");
        stringBuilder.append(member.getName()).append(",");
        stringBuilder.append(member.getAge()).append(",");
        stringBuilder.append(member.getClazz().name()).append("\n");

        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

    }
}
