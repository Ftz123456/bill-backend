package com.zsc.edu.bill.modules.system.mapper;

import com.zsc.edu.bill.modules.system.dto.UserCreateDto;
import com.zsc.edu.bill.modules.system.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-20T14:48:01+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserCreateDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserCreateDto userCreateDto = new UserCreateDto();

        userCreateDto.setUsername( entity.getUsername() );
        userCreateDto.setPassword( entity.getPassword() );
        userCreateDto.setPhone( entity.getPhone() );
        userCreateDto.setEmail( entity.getEmail() );
        userCreateDto.setDeptId( entity.getDeptId() );
        userCreateDto.setNickName( entity.getNickName() );
        userCreateDto.setAvatar( entity.getAvatar() );
        userCreateDto.setAddress( entity.getAddress() );
        userCreateDto.setRemark( entity.getRemark() );

        return userCreateDto;
    }

    @Override
    public User toEntity(UserCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setRemark( dto.getRemark() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        user.setPhone( dto.getPhone() );
        user.setEmail( dto.getEmail() );
        user.setNickName( dto.getNickName() );
        user.setDeptId( dto.getDeptId() );
        user.setAvatar( dto.getAvatar() );
        user.setAddress( dto.getAddress() );

        return user;
    }

    @Override
    public List<UserCreateDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserCreateDto> list = new ArrayList<UserCreateDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntity(List<UserCreateDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserCreateDto userCreateDto : dtoList ) {
            list.add( toEntity( userCreateDto ) );
        }

        return list;
    }

    @Override
    public void convert(UserCreateDto dto, User entity) {
        if ( dto == null ) {
            return;
        }

        entity.setRemark( dto.getRemark() );
        entity.setUsername( dto.getUsername() );
        entity.setPassword( dto.getPassword() );
        entity.setPhone( dto.getPhone() );
        entity.setEmail( dto.getEmail() );
        entity.setNickName( dto.getNickName() );
        entity.setDeptId( dto.getDeptId() );
        entity.setAvatar( dto.getAvatar() );
        entity.setAddress( dto.getAddress() );
    }
}
