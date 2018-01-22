package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理Service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService
{
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId)
    {
        //根据父节点id查询子列表
        TbItemCatExample itemCatExample = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = itemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCatList = itemCatMapper.selectByExample(itemCatExample);
        //转换成EasyUITreeNode
        List<EasyUITreeNode> easyUITreeNodeList = new ArrayList<>();
        for (TbItemCat itemCat:itemCatList)
        {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            //如果节点下有子节点则“closed”，如果没有子节点则“open”
            treeNode.setState(itemCat.getIsParent()?"closed":"open");
            easyUITreeNodeList.add(treeNode);
        }
        return easyUITreeNodeList;
    }

}
