package com.huntermuze.userservice.controller;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.dto.container.Users;
import com.huntermuze.userservice.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsersTest() throws Exception {
        // given
        List<User> userList = List.of(
                new User(3, "Hasbulla", "Bankstown", 19, "UFC Fighter", "hasby@gmail.com", "01431949914"),
                new User(1, "Abdu Rozik", "Westside", 19, "UFC Fighter & Singer", "abdu@gmail.com", "08194148149")
        );
        Users users = new Users(userList);
        // when
        userService.getAllUsers();
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        // then
        Mockito.verify(userService).getAllUsers();
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userList", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userList[0].name", Matchers.is("Hasbulla")));
    }

    @Test
    public void getUserTest() throws Exception {
        // given
        long id = 3;
        User user = new User(id, "Hasbulla", "Bankstown", 19, "UFC Fighter", "hasby@gmail.com", "01431949914");
        // when
        userService.getUser(String.valueOf(id));
        Mockito.when(userService.getUser(String.valueOf(id))).thenReturn(user);
        // then
        Mockito.verify(userService).getUser(String.valueOf(id));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Hasbulla")));
    }

    @Test
    public void deleteUserTest() throws Exception {
        // given
        long id = 83;
        // when
        userService.deleteUser(String.valueOf(id));
        // then
        Mockito.verify(userService).deleteUser(String.valueOf(id));
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
