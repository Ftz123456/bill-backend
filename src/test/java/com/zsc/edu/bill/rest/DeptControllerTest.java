package com.zsc.edu.bill.rest;

import com.zsc.edu.bill.MockMvcConfigBase;
import com.zsc.edu.bill.domain.DeptBuilder;
import com.zsc.edu.bill.modules.system.controller.DeptController;
import com.zsc.edu.bill.modules.system.dto.DeptDto;
import com.zsc.edu.bill.modules.system.entity.Dept;
import com.zsc.edu.bill.modules.system.mapper.DeptMapper;
import com.zsc.edu.bill.modules.system.service.DeptService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeptController.class)
public class DeptControllerTest extends MockMvcConfigBase {

    @Spy
    private static Dept dept1;

    private static Dept dept2;

    @MockBean
    protected DeptMapper deptMapper;

    @MockBean
    private DeptService service;

    @BeforeAll
    static void beforeAll() {
        dept1 = DeptBuilder.aDept().name("测试部门1").build();
        dept1.setId(1L);
        dept2 = DeptBuilder.aDept().name("测试部门2").pid(dept1.id).build();
    }

    @Test
    void create() throws Exception{
        DeptDto dto = new DeptDto();
        dto.name  = dept1.getName();
        when(service.create(any())).thenReturn(dept1);
        mockMvc.perform(post("/api/rest/dept")
                .with(csrf().asHeader())
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
            ).andExpect(status().isOk()).andDo(print());
        verify(service).create(any());
    }

    @Test
    void list() throws Exception {
        List<Dept> depts = Lists.newArrayList(dept1, dept2);
        when(service.list()).thenReturn(depts);
        mockMvc.perform(get("/api/rest/dept").with(user(userDetails)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service).list();
    }

    @Test
    void update() throws Exception {
        DeptDto dto = new DeptDto();
        dto.name  = dept1.getName();
        when(service.edit(any(), any())).thenReturn(true);
        mockMvc.perform(patch("/api/rest/dept/1")
                    .with(csrf().asHeader())
                    .with(user(userDetails))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk()).andDo(print());
        verify(service).edit(any(), any());
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
