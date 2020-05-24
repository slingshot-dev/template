package fr.axa.editique.users.service.impl;

import fr.axa.editique.users.dto.UserDTO;
import fr.axa.editique.users.model.User;
import fr.axa.editique.users.repository.users.UserRepository;
import fr.axa.editique.users.service.api.UserService;
import fr.axa.editique.users.service.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    private UserService userService ;

    @Before
    public void init(){
        userService = new UserServiceImpl(userRepository,userMapper);
        initUsers();
        initUsersDTO();
    }

    private void initUsersDTO() {
        List<UserDTO> userDTOList = Arrays.asList(new UserDTO());
        Mockito.doReturn(userDTOList).when(userMapper).entityToDTO(Arrays.asList(new User()));
    }

    private void initUsers() {
        List<User> expectedUser = Arrays.asList(new User());
        Mockito.doReturn(expectedUser).when(userRepository).findAllById(Arrays.asList(Long.valueOf(1)));
    }

    @Test
    public void whenFindAll_thenReturnUserList() {
        List<UserDTO> actualUsers = userService.findAll(Arrays.asList(Long.valueOf(1)));
        Assert.assertTrue("no users found when users.findAll() runs", actualUsers.size()>0);
    }
}
