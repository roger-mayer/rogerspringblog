package com.codeup.rogerspringblog.services;


import com.codeup.rogerspringblog.repositories.PostRepository;
import com.codeup.rogerspringblog.models.Post;
import com.codeup.rogerspringblog.models.User;
import com.codeup.rogerspringblog.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PostsIntegrationTest {

    // dependencies...

    @Autowired
    private PostRepository postsDao;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder pen;

    @Autowired
    private MockMvc mvc;

    private User testUser;

    private HttpSession session;

    private void setUpTestUser() {
        this.testUser = userDao.findByUsername("testUser");
        if (testUser == null) {
            testUser = new User();
            testUser.setEmail("test@email.com");
            testUser.setPassword(pen.encode("password"));
            testUser.setUsername("testUser");
            this.testUser = userDao.save(testUser);
        }
    }

    private void setUpSession() throws Exception {
        this.session = mvc.perform(post("/login").with(csrf()).param("username", testUser.getUsername()).param("password", "password"))
                .andExpect(status().isFound())
                .andReturn()
                .getRequest()
                .getSession();
    }

    // create and login as test user
    @Before
    public void setUp() throws Exception {
        setUpTestUser();
        setUpSession();
    }

    // sanity test
    @Test
    public void testContext() {
        assertNotNull(mvc);
        assertNotNull(session);
    }

    /**
     * To test entity creation, check for a 3xx redirection
     */
    @Test
    public void testPostCreation() throws Exception {
        mvc.perform(
                post("/posts/create")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .param("title", "Test Title")
                        .param("description", "This is a Test Post")
        )
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        // clean up
        Post p = postsDao.findByTitle("Test Title");
        postsDao.deleteById(p.getId());
    }

    /**
     * To test entity show, check for OK status and the content matching a given entity
     */
    @Test
    public void testPostShow() throws Exception {
        // select first post to test
        Post postToTestShow = postsDao.findAll().get(0);

        // complete and verify request to show it
        mvc.perform(
                get("/posts/" + postToTestShow.getId())
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(postToTestShow.getTitle())))
                .andExpect(content().string(containsString(postToTestShow.getDescription())));
    }

    /**
     * To test entity index, check for OK status and the content matching a random entity
     */
    @Test
    public void testPostIndex() throws Exception {
        // select first post to test
        Post postToTestShow = postsDao.findAll().get(0);

        // complete and verify request to show it in the index
        mvc.perform(
                get("/posts")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(postToTestShow.getTitle())))
                .andExpect(content().string(containsString(postToTestShow.getDescription())));
    }

    /**
     * To test entity edit, check for redirect and updated content for show
     */
    @Test
    public void testPostEdit() throws Exception {

        // select an post to edit and keep the original values for later clean up
        Post updatePost = postsDao.findAll().get(0);

        String originalTitle = updatePost.getTitle();
        String originalDescription = updatePost.getDescription();

        updatePost.setTitle("Updated Title");
        updatePost.setDescription("This is the updated description!");

        // execute the update
        mvc.perform(
                post("/posts/" + updatePost.getId() + "/edit").with(csrf()).session((MockHttpSession) session)
                        .param("title", updatePost.getTitle())
                        .param("description", updatePost.getDescription())
        )
                .andExpect(status().is3xxRedirection());

        // verify the change in the post show
        mvc.perform(get("/posts/" + updatePost.getId()))
                .andExpect(view().name("posts/show"))
                .andExpect(content().string(containsString(updatePost.getDescription())))
                .andExpect(content().string(containsString(updatePost.getTitle())));

        // clean up by setting post back post original values
        mvc.perform(
                post("/posts/" + updatePost.getId() + "/edit").with(csrf()).session((MockHttpSession) session)
                        .param("title", originalTitle)
                        .param("description", originalDescription)
        );

    }


    /**
     * To test entity delete, check for redirect and that the index lacks content for the deleted entity
     */
    @Test
    public void testPostDelete() throws Exception {

        // create post to destroy
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) session)
                        .param("title", "Delete Title")
                        .param("description", "Post to delete")
        )
                .andExpect(status().is3xxRedirection());

        // get newly created post
        Post postToDelete = postsDao.findByTitle("Delete Title");

        // attempt delete of post
        mvc.perform(
                post("/posts/" + postToDelete.getId() + "/delete").with(csrf()).session((MockHttpSession) session)
        )
                .andExpect(status().is3xxRedirection());

        // verify that it does not exist in the index of posts
        mvc.perform(
                get("/posts").session((MockHttpSession) session)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Test Title"))));
    }

    // remove test user
    @After
    public void cleanUp() {
        userDao.delete(testUser);
    }


}