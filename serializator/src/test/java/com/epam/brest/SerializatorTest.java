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

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Date;
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
    List<Request> listRequest = new ArrayList<>();
    List<Admin> listAdmin = new ArrayList<>();
    List<Groupe> listGroupe = new ArrayList<>();
    String fileUser = "user.sql";
    String fileRequest = "request.sql";
    String fileAdmin = "admin.sql";
    String fileGroupe = "groupe.sql";

    public SerializatorTest(@Autowired Serializator serializator) {
        this.serializator = serializator;
    }

    @Test
    void testFindSerializator() {
        logger.debug("Execute test: IsSerializator()");
        assertNotNull(serializator);
    }

    @Test
    void testUserSerializator() throws InvalidObjectException {
        logger.debug("Execute test: testUserSerializator()");
        User user = new User("Mike", "mike", "1111", "oleg@mail.com");
        listUser.add(user);
        serializator.serialization(listUser, fileUser);
        List<User> listTest = serializator.deserialization(fileUser);
        User user1 = listTest.get(0);
        assertTrue(user.getName().equals(user1.getName()));
        assertTrue(user.getLogin().equals(user1.getLogin()));
    }

    @Test
    void testRequestSerializator() throws InvalidObjectException {
        logger.debug("Execute test: testRequestSerializator()");
        assertNotNull(serializator);
        Request request = new Request(2,"e1", "2", "math", new Date());
        listRequest.add(request);
        serializator.serialization(listRequest, fileRequest);
        List<Request> listTest = serializator.deserialization(fileRequest);
        Request request1 = listTest.get(0);
        assertTrue(request.getGroupe().equals(request1.getGroupe()));
    }

    @Test
    void testAdminSerializator() throws InvalidObjectException {
        logger.debug("Execute test: testAdminSerializator()");
        Admin admin = new Admin(1,"Mike", "mike", "1111", "oleg@mail.com");
        listAdmin.add(admin);
        serializator.serialization(listAdmin, fileAdmin);
        List<Admin> listTest = serializator.deserialization(fileAdmin);
        Admin admin1 = listTest.get(0);
        assertTrue(admin.getName().equals(admin1.getName()));
    }


    @Test
    void testGroupeSerializator() throws InvalidObjectException {
        logger.debug("Execute test: testGroupeSerializator()");
        assertNotNull(serializator);
        Groupe groupe = new Groupe("e1");
        listGroupe.add(groupe);
        serializator.serialization(listGroupe, fileGroupe);
        List<Groupe> listTest = serializator.deserialization(fileGroupe);
        Groupe groupe1 = listTest.get(0);
        assertTrue(groupe.getNameGroupe().equals(groupe1.getNameGroupe()));
    }
}
