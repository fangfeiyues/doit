package com.fang.doit.algo.tree;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by Feiyue on 2020/8/12 6:03 PM
 */
public class VersionTree {

    public List<TreeNode> buildTree(Long version) {
        List<TreeNode> allList = new ArrayList();
        Map<Long, TreeNode> allMap = new HashMap<>();
        List<Long> parentIdList = Arrays.asList(0L);

        while (!CollectionUtils.isEmpty(parentIdList)) {
            List<TreeNodeDO> treeNodeDOList = getByParentIdList(parentIdList, version);
            if (CollectionUtils.isEmpty(treeNodeDOList)) {
                break;
            }
            Map<Long, Optional<TreeNodeDO>> map = treeNodeDOList.stream().collect(Collectors.groupingBy(TreeNodeDO::getId,
                    Collectors.reducing((s1, s2) -> s1.getVersion() > s2.getVersion() ? s1 : s2)));
            List<TreeNodeDO> list = map.values().stream().map(each -> each.get()).collect(Collectors.toList());
            parentIdList = list.stream().map(TreeNodeDO::getParentId).collect(Collectors.toList());
            List<TreeNode> currentLevelTreeNodeList = list.stream().map(each -> {
                TreeNode treeNode = new TreeNode(each.getId(), each.getStatus(), each.getVersion());
                Long parentId = each.getParentId();
                if (parentId == 0) {
                    return treeNode;
                }
                TreeNode parentTreeNode = allMap.get(parentId);
                treeNode.setPre(parentTreeNode);
                return parentTreeNode;
            }).collect(Collectors.toList());
            allList.addAll(currentLevelTreeNodeList);

            Map<Long, TreeNode> currentLevelTreeNodeMap = currentLevelTreeNodeList.stream().collect(Collectors.toMap(each -> each.getId(), each -> each));
            allMap.putAll(currentLevelTreeNodeMap);
        }
        return allList;
    }

    public TreeNode buildPathNode(Long currentNodeId, Long version) {
        TreeNode headTreeNode = null;
        TreeNode currentNode = null;
        while (currentNodeId != null && currentNodeId > 0) {
            TreeNodeDO treeNodeDO = getById(currentNodeId, version);
            TreeNode treeNodeNew = new TreeNode(treeNodeDO.getId(), treeNodeDO.getStatus(), treeNodeDO.getVersion());
            if (headTreeNode == null) {
                headTreeNode = treeNodeNew;
            }
            if (currentNode == null) {
                currentNode = treeNodeNew;
            } else {
                currentNode.setPre(treeNodeNew);
                currentNode = treeNodeNew;
            }
            currentNodeId = treeNodeDO.getParentId();
        }
        return headTreeNode;
    }


    private TreeNodeDO getById(Long currentNodeId, Long version) {
        //sql:select * from xx where id=currentNodeId and version <=version order by version desc limit 1
        return new TreeNodeDO();
    }

    private List<TreeNodeDO> getByParentId(Long parentId, Long version) {
        //sql:select * from xx where parent_id=parentId and version <= version order by version desc
        return new ArrayList<>();
    }

    private List<TreeNodeDO> getByParentIdList(List<Long> parentIdList, Long version) {
        //sql:select * from xx where parent_id in () and version <=version order by version desc
        return new ArrayList<>();
    }

    public static class TreeNodeDO {

        private Long id;

        private Integer status;

        private Long parentId;

        private Long version;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public Long getVersion() {
            return version;
        }

        public void setVersion(Long version) {
            this.version = version;
        }
    }

    public static class TreeNode {

        private Long id;

        private Integer status;

        private TreeNode pre;

        private Long version;

        TreeNode(Long id, Integer status, Long version) {
            this.id = id;
            this.status = status;
            this.version = version;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }


        public Long getVersion() {
            return version;
        }

        public void setVersion(Long version) {
            this.version = version;
        }

        public TreeNode getPre() {
            return pre;
        }

        public void setPre(TreeNode pre) {
            this.pre = pre;
        }
    }


}
