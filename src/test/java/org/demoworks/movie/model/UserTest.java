package org.demoworks.movie.model;

import org.demoworks.movie.model.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User critic;
    User user;
    User undefinedRole;

    @Before
    public void setUp(){

        critic = new User();
        user = new User();
        undefinedRole = new User();

        critic.setRole(Role.CRITIC);
        user.setRole(Role.USER);
    }

    @Test
    public void isCritic_ThreeRoles_Test() {

        assertFalse(user.isCritic());
        assertFalse(undefinedRole.isCritic());
        assertTrue(critic.isCritic());

    }
}