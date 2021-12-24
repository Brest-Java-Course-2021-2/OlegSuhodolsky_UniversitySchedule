package com.epam.brest;

import com.epam.brest.model.entity.Admin;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:serializator-test-conf.xml", "classpath*:test-jdbc-conf.xml"})
//@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback
public class SerializatorTest {
    private final Logger logger = LogManager.getLogger(SerializatorTest.class);
    private Serializator serializator;

    List<User> listUser = new ArrayList<>();
    List<Request> listRequest;
    List<Admin> listAdmin;
    List<Groupe> listGroupe;
    String fileUser = "user.sql";
    String fileRequest = "request.sql";
    String fileAdmin = "admin.sql";
    String fileGroupe = "groupe.sql";

    public SerializatorTest(@Autowired Serializator serializator) {
        this.serializator = serializator;
    }

    @Test
    void testFindSerializator() {
        logger.debug("Execute test: getAll()");
        assertNotNull(serializator);

    }

    @Test
    void testUserSerializator() throws InvalidObjectException {
        logger.debug("Execute test: getAll()");
        assertNotNull(serializator);
        User user = new User("Mike", "mike", "1111", "oleg@mail.com");
        listUser.add(user);
        serializator.serialization(listUser, fileUser);
        List<User> listTest = serializator.deserialization(fileUser);
        User user1 = listTest.get(0);
        assertTrue(user.getName().equals(user1.getName()));
    }




}
