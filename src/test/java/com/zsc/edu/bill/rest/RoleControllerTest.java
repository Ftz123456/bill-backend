package com.zsc.edu.bill.rest;

import com.zsc.edu.bill.MockMvcConfigBase;
import com.zsc.edu.bill.domain.RoleBuilder;
import com.zsc.edu.bill.modules.system.controller.RoleController;
import com.zsc.edu.bill.modules.system.dto.RoleDto;
import com.zsc.edu.bill.modules.system.entity.Role;
import com.zsc.edu.bill.modules.system.service.RoleService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoleController.class)
public class RoleControllerTest extends MockMvcConfigBase {

    private static Role Role1;

    private static Role Role2;

    @MockBean
    private RoleService service;

    @BeforeAll
    static void beforeAll() {
        Role1 = RoleBuilder.aRole().name("管理员").build();
        Role2 = RoleBuilder.aRole().name("普通用户").build();
    }

    @Test
    void create() throws Exception{
        RoleDto dto = new RoleDto();
        dto.name  = Role1.getName();
        when(service.save(any())).thenReturn(true);
        mockMvc.perform(post("/api/rest/role")
                .with(csrf().asHeader())
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
            ).andExpect(status().isOk()).andDo(print());
        verify(service).save(any());
    }

    @Test
    void list() throws Exception {
        List<Role> Roles = Lists.newArrayList(Role1, Role2);
        when(service.list()).thenReturn(Roles);
        mockMvc.perform(get("/api/rest/Role").with(user(userDetails)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service).list();
    }

    @Test
    void update() throws Exception {
        RoleDto dto = new RoleDto();
        dto.name  = Role1.getName();
        when(service.update(any(), any())).thenReturn(true);
        mockMvc.perform(patch("/api/rest/Role/{id}", Role1.id)
                    .with(csrf().asHeader())
                    .with(user(userDetails))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk()).andDo(print());
        verify(service).update(any(), any());
    }
//
//    @Test
//    void remove() throws Exception {
//        when(service.deleteById(anyLong())).thenReturn(lampGroup1);
//        mockMvc.perform(delete("/api/rest/lamp-group/{id}", lampGroup1.id)
//                .with(csrf().asHeader())
//                .with(user(userDetails))
//        )
//                .andExpect(status().isOk())
//                .andDo(document("lamp-group/delete"));
//        verify(service).deleteById(anyLong());
//    }
//
//    @Test
//    void detail() throws Exception {
//        when(service.detail(anyLong())).thenReturn(lampGroup1);
//        mockMvc.perform(get("/api/rest/lamp-group/{id}", lampGroup1.id)
//                .with(user(userDetails))
//        )
//                .andExpect(status().isOk())
//                .andDo(document("lamp-group/detail"));
//        verify(service).detail(anyLong());
//    }
//
//    @Test
//    void batchDelete() throws Exception {
//        Long[] ids = new Long[]{1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l};
//        when(service.batchDelete(any())).thenReturn(ids);
//        mockMvc.perform(post("/api/rest/lamp-group/batchRemove")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(ids))
//                .with(csrf().asHeader())
//                .with(user(userDetails))
//        )
//                .andExpect(status().isOk())
//                .andDo(document("lamp-group/batchRemove"));
//        verify(service).batchDelete(any());
//    }

}
