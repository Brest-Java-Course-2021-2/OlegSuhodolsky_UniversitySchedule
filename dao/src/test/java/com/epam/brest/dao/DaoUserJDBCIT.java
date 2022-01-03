package com.epam.brest.dao;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback
class DaoUserJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoUserJDBCIT.class);

    private DaoUser daoUser;

    public DaoUserJDBCIT(@Autowired DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Test
    void testFindAll() {
        logger.debug("Execute test: getAll()");
        assertNotNull(daoUser);
        assertNotNull(daoUser.getAllUsers());
        assertTrue(daoUser.getAllUsers().size() > 0, "Size > 0");
    }

    @Test
    void testInsert() {
        logger.debug("Execute test: write()");
        assertNotNull(daoUser.writeUser(new User("John", "john", "1111", "isocrol@yandex.ru")));
        assertTrue(daoUser.getAllUsers().size() > 0, "Size > 0");
    }


    @Test
    void testInsertAfterSerialization() {
        logger.debug("Execute test: writeSerialize()");
        assertNotNull(daoUser.writeUserSerialize(new User(22,"John", "john", "1111", "isocrol@yandex.ru")));
        assertTrue(daoUser.getAllUsers().size() > 0, "Size > 0");
    }

    @Test
    void testChange() {
        logger.debug("Execute test: update()");
        daoUser.updateUser(new User(1, "Max", "john", "1111", "isocrol@yandex.ru"));
        List<User> user = daoUser.getAllUsers();
        assertTrue(daoUser.getAllUsers().size() > 0, "Size > 0");

    }

    @Test
    void testDelete() {
        logger.debug("Execute test: delete({id})");
        List<User> user = daoUser.getAllUsers();
        daoUser.deleteUser(1);
        assertEquals(daoUser.getAllUsers().size(), user.size() - 1);
    }

    @Test
    void testRead() {
        logger.debug("Execute test: read({id})");
        User user = daoUser.getAllUsers().get(0);
        assertEquals(daoUser.readUser(1).getName(), user.getName());
    }

}
