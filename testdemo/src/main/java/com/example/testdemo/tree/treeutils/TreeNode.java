package com.example.testdemo.tree.treeutils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/20.
 */
public class TreeNode {
    private int id;
    private int pId = 0;
    private String name;
    /**
     * 是否展开
     */
    private boolean isExpand = false;

    private int icon;
    /**
     * 树的层级
     */
    private int level;
    /**
     * 父节点
     */
    private TreeNode parentNode;
    private List<TreeNode> childNode = new ArrayList<>();

    public TreeNode(int id, String name, int pId) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

    public List<TreeNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<TreeNode> childNode) {
        this.childNode = childNode;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

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

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
    /**
     * 是否是根节点
     * */
    public boolean isRoot(){
        return parentNode==null;
    }
    /**
     * 父节点是否展开
     * */
    public boolean isParentExpand(){
        if(parentNode==null)
            return false;
        return parentNode.isExpand();
    }
    /**
     * 是否是叶子节点
     * */
    public boolean isLeaf(){
        return childNode.size()==0;
    }
    /**
     * 得到当前Node的层级
     */
    public int getLevel() {
        return parentNode==null?0:parentNode.getLevel()+1 ;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if(!isExpand){
            for(TreeNode node :childNode){
                node.setExpand(false);
            }
        }
    }
}
