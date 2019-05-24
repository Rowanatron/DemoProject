package org.demoworks.movie.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

public class JWTUtilTest {

    private JWTUtil jwtUtil;
    private String token;

    @Before
    public void setUp(){

        jwtUtil = new JWTUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "IsThisG00DEnoughForYou?JWTNonsense");
    }

    @Test
    public void getUsernameFromToken_ExpectedRowan_Test() {

        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlJvd2FuIn0.eFkl6rQJ9vY7MoKOiuNhq28cuo0vq38XAl2202j95L8";
        assertEquals("Rowan",jwtUtil.getUsernameFromToken(token));

    }
}