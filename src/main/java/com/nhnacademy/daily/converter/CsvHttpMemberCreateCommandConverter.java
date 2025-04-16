package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.ClassType;
import com.nhnacademy.daily.model.MemberCreateCommand;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CsvHttpMemberCreateCommandConverter extends AbstractHttpMessageConverter<MemberCreateCommand> {

    public CsvHttpMemberCreateCommandConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return MemberCreateCommand.class.isAssignableFrom(clazz);
    }

    @Override
    protected MemberCreateCommand readInternal(Class<? extends MemberCreateCommand> clazz,
                                               HttpInputMessage inputMessage) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line = reader.readLine();
            String[] tokens = line.split(",");

            return new MemberCreateCommand(
                    tokens[0],
                    tokens[1],
                    Integer.parseInt(tokens[2]),
                    ClassType.valueOf(tokens[3])
            );
        }
    }

    @Override
    protected void writeInternal(MemberCreateCommand member,
                                 HttpOutputMessage outputMessage) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("id,name,age,class\n")
                .append(member.getId()).append(",")
                .append(member.getName()).append(",")
                .append(member.getAge()).append(",")
                .append(member.getClazz().name());

        OutputStream os = outputMessage.getBody();
        os.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
    }
}
