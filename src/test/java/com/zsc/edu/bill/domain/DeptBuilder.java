package com.zsc.edu.bill.domain;

import com.zsc.edu.bill.modules.system.entity.Dept;

import java.util.HashSet;

public class DeptBuilder extends BaseEntityBuilder {

    public String name;
    public Dept parent;
    public Long pid;

    public HashSet<Dept> children;


    public static DeptBuilder aDept(){
        return new DeptBuilder();
    }

    public DeptBuilder name(String name){
        this.name = name;
        return this;
    }


    public DeptBuilder parent(Dept parent) {
        this.parent = parent;
        return this;
    }

    public DeptBuilder pid(Long pid) {
        this.pid = pid;
        return this;
    }

    public DeptBuilder children(HashSet<Dept> children) {
        this.children = children;
        return this;
    }


    public Dept build(){
        Dept dept = new Dept();
        dept.setName(name);
        dept.setPid(pid);
        dept.setChildren(children);
        return dept;
    }


}
