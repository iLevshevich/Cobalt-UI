package com.cobalt.helpers.xml.parser;

import com.cobalt.helpers.xml.entities.Users;
import lombok.NonNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.util.Objects;

public class UsersParser {
    public static Users getUsers(@NonNull final InputStreamReader inputStreamReader) throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(Users.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Users result = (Users) unmarshaller.unmarshal(inputStreamReader);
        Objects.requireNonNull(result);

        return result;
    }
}
