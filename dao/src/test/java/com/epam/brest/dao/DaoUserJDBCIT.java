package com.epam.brest.dao;

import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DaoUserJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoUserJDBCIT.class);

    private DaoUser daoUser;

    public DaoUserJDBCIT(@Autowired DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: getAll()");
        assertNotNull(daoUser);
        assertNotNull(daoUser.getAll());
        assertTrue(daoUser.getAll().size() == 2, "Size = 2");
    }

    @Test
    void insert() {

        logger.debug("Execute test: write()");
        assertNotNull(daoUser.write(new User("John", "john", "1111", "isocrol@yandex.ru")));
        assertTrue(daoUser.getAll().size() == 3, "Size = 3");
    }

    @Test
    void testChange() {
        logger.debug("Execute test: update()");
        daoUser.updateUser(new User(1, "Max", "john", "1111", "isocrol@yandex.ru"));
        List<User> user = daoUser.getAll();
        assertTrue(daoUser.getAll().size() == 2, "Size = 3");

    }

    @Test
    void testDelete() {
        logger.debug("Execute test: delete({id})");
        List<User> user = daoUser.getAll();
        daoUser.delete(1);
        assertEquals(daoUser.getAll().size(), user.size() - 1);
    }

    @Test
    void testRead() {
        logger.debug("Execute test: read({id})");
        User user = daoUser.getAll().get(0);
        assertEquals(daoUser.read(1).getName(), user.getName());
    }

}
