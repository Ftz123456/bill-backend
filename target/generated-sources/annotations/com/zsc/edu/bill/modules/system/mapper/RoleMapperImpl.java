package com.zsc.edu.bill.modules.system.mapper;

import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Authority;
import com.zsc.edu.bill.modules.system.entity.Role;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-20T14:48:00+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setName( entity.getName() );
        roleDto.setRemark( entity.getRemark() );
        Set<Authority> set = entity.getAuthorities();
        if ( set != null ) {
            roleDto.setAuthorities( new LinkedHashSet<Authority>( set ) );
        }

        return roleDto;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRemark( dto.getRemark() );
        role.setName( dto.getName() );
        Set<Authority> set = dto.getAuthorities();
        if ( set != null ) {
            role.setAuthorities( new LinkedHashSet<Authority>( set ) );
        }

        return role;
    }

    @Override
    public List<RoleDto> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public List<Role> toEntity(List<RoleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleDto roleDto : dtoList ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public void convert(RoleDto dto, Role entity) {
        if ( dto == null ) {
            return;
        }

        entity.setRemark( dto.getRemark() );
        entity.setName( dto.getName() );
        if ( entity.getAuthorities() != null ) {
            Set<Authority> set = dto.getAuthorities();
            if ( set != null ) {
                entity.getAuthorities().clear();
                entity.getAuthorities().addAll( set );
            }
            else {
                entity.setAuthorities( null );
            }
        }
        else {
            Set<Authority> set = dto.getAuthorities();
            if ( set != null ) {
                entity.setAuthorities( new LinkedHashSet<Authority>( set ) );
            }
        }
    }
}
