package com.codeup.rogerspringblog.services;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceTest {
    private EmailService emailService;

    @Before
    public void setUp(){
        emailService = new EmailService();
    }

    @Test
    public void srvNotNull(){
        assertNotNull(emailService);
    }
    @Test
    public void twoIsTwo(){
        assertEquals(2,2);
    }

}
