package com.codeup.rogerspringblog.services;

import com.codeup.rogerspringblog.RogerspringblogApplication;
import com.codeup.rogerspringblog.models.User;
import com.codeup.rogerspringblog.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= RogerspringblogApplication.class)
@AutoConfigureMockMvc
public class AboutPageIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;
    private PasswordEncoder pen;

    private User testUser;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        testUser = userDao.findByUsername("testUser");
        if (testUser == null){
            User user = new User();
            user.setUsername("testUser");
            user.setEmail("test@email.com");
            user.setPassword(pen.encode("password"));
            testUser = userDao.save(user);

        }
        System.out.println("test user name"+ testUser.getUsername());
        System.out.println("test user name"+ testUser.getPassword());
        session = mvc.perform(post("/login").with(csrf())
            .param("username", testUser.getUsername())
            .param("password", testUser.getPassword()))
                .andExpect(status().isFound()).andReturn().getRequest().getSession();

    }

    @Test
    public void twoIsTwo(){
        assertEquals(2,2);
    }

    @Test
    public void mvcNotNull(){
        assertNotNull(mvc);
    }

//    @Test
//    public void testAboutPage() throws Exception {
//        mvc.perform(get("/about").session((MockHttpSession)session))
//            .andExpect(status().isOk())
//            .andExpect(view().name("about")
//            .andExpect(content().string(containsString("about"))));
//    }




    @After
    public void cleanUp(){
        userDao.delete(testUser);
    }
}
