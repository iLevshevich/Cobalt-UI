package com.cobalt.helpers;

import com.cobalt.helpers.xml.entities.Users;
import com.cobalt.helpers.xml.parser.UsersParser;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Objects;

@Component
public class UserHelper {
    private final PathHelper helper;

    @Getter
    private Users users = null;

    @Autowired
    public UserHelper(@NonNull final PathHelper helper) {
        this.helper = helper;
    }

    @PostConstruct
    private void setCommands() throws JAXBException, IOException {
        if (Objects.isNull(users)) {
            final String cobaltSecurityLocation = helper.getCobaltSecurityLocation();
            final Class<?> clazz = this.getClass();
            try (final InputStream inputStream = new FileInputStream(cobaltSecurityLocation)) {
                try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
                    users = UsersParser.getUsers(inputStreamReader);
                }
            }
        }
    }
}
