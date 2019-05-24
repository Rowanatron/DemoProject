package org.demoworks.movie.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.demoworks.movie.model.User;
import org.demoworks.movie.model.enums.Role;

@Data
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String email;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Enum<Role> roles;


    public static User toEntitiy(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRoles());
        return user;
    }

    public static UserDto fromEntity(User user){
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(),user.getRole());
    }

}