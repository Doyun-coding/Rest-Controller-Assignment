package com.nhnacademy.daily.converter;

import com.nhnacademy.daily.model.ProjectCreateCommand;
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

public class CsvHttpProjectCreateCommandConverter extends AbstractHttpMessageConverter<ProjectCreateCommand> {
    public CsvHttpProjectCreateCommandConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ProjectCreateCommand.class.isAssignableFrom(clazz);
    }

    @Override
    protected ProjectCreateCommand readInternal(Class<? extends ProjectCreateCommand> clazz,
                                                HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            String line = reader.readLine();
            String[] tokens = line.split(",");

            String code = tokens[0];
            ProjectType type = ProjectType.valueOf(tokens[1]);

            return new ProjectCreateCommand(code, type);
        }
    }

    @Override
    protected void writeInternal(ProjectCreateCommand command,
                                 HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("code,type\n")
                .append(command.getCode()).append(",")
                .append(command.getProjectType().name());

        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
