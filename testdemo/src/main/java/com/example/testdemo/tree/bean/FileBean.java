package com.example.testdemo.tree.bean;

import com.example.testdemo.tree.annotation.TreeNodeId;
import com.example.testdemo.tree.annotation.TreeNodeName;
import com.example.testdemo.tree.annotation.TreeNodePid;

/**
 * Created by mym_0314 on 2016/5/20.
 */
public class FileBean {
    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pId;
    @TreeNodeName
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
}
