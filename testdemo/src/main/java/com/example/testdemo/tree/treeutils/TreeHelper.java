package com.example.testdemo.tree.treeutils;

import com.example.testdemo.R;
import com.example.testdemo.tree.annotation.TreeNodeId;
import com.example.testdemo.tree.annotation.TreeNodeName;
import com.example.testdemo.tree.annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/20.
 */
public class TreeHelper {
    /**
     * 将用户的普通Bean使用  注解+反射  转换成TreeNode 对象
     */
    public static <T> List<TreeNode> convertBean2Node(List<T> datas) throws IllegalAccessException {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode node ;
        int id = -1;
        int pId = -1;
        String name = null;
        for (T t : datas) {
            Class cls = t.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodePid.class) != null) {
                    field.setAccessible(true);
                    pId = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeName.class) != null) {
                    field.setAccessible(true);
                    name = (String) field.get(t);
                }
                node = new TreeNode(id, name, pId);
                nodes.add(node);
            }
        }
        /**
         * 设置TreeNode 之间的父子关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                TreeNode m = nodes.get(j);
                if (m.getId() == n.getpId()) {
                    m.getChildNode().add(n);
                    n.setParentNode(m);
                } else if (m.getId() == n.getId()) {
                    n.getChildNode().add(m);
                    m.setParentNode(n);
                }
            }
        }
        /**
         * 设置每个TreeNode 的展开图片
         * */
        for (TreeNode n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    /**
     * 过滤初始状态下可以显示的TreeNode
     */
    public static List<TreeNode> filterVisiableNode(List<TreeNode> nodes) {
        List<TreeNode> result = new ArrayList<>();
        for (TreeNode node : result) {
            if (node.isRoot() || node.getParentNode().isExpand()) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 设置TreeNode 的展开图片
     */
    private static void setNodeIcon(TreeNode n) {
        if (n.getChildNode().size() > 0 && n.isExpand()) {
            n.setIcon(R.mipmap.tree_ex);
        } else if (n.getChildNode().size() > 0 && !n.isExpand()) {
            n.setIcon(R.mipmap.tree_ec);
        } else {
            n.setIcon(-1);
        }
    }

    /**
     * 将转换后的结果，深度遍历，然后顺序存放在
     */
    public static <T> List<TreeNode> sortedData(List<T> datas, int defaultLevel) throws IllegalAccessException {
        List<TreeNode> result = new ArrayList<>();
        List<TreeNode> nodes = convertBean2Node(datas);
        List<TreeNode> rootNodes = getRootNodes(nodes);
        for (TreeNode node : rootNodes) {
            addNodes(result, node, defaultLevel, 1);
        }
        return null;
    }

    /**
     *递归遍历并且顺序添加到result结果中
     *
     */
    private static void addNodes(List<TreeNode> result, TreeNode node, int defaultLevel, int currentLevel) {
        result.add(node);
        if (defaultLevel > currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildNode().size(); i++) {
            addNodes(result, node.getChildNode().get(i), defaultLevel, currentLevel + 1);
        }
    }

    /**
     * 得到根节点
     */
    private static List<TreeNode> getRootNodes(List<TreeNode> nodes) {
        List<TreeNode> roots = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node.isRoot())
                roots.add(node);
        }
        return roots;
    }


}
