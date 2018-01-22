package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value="/item/save", method= RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc) {
		TaotaoResult result = itemService.addItem(item, desc);
		return result;
	}

	@RequestMapping(value = "/rest/page/item-edit")
	public String itemEdit()
	{
		return "item-edit";
	}

	@RequestMapping(value = "/rest/item/param/item/query/{id}")
	public TaotaoResult getItem(@PathVariable("id") long itemId)
	{
		TbItem tbItem = itemService.getItemById(itemId);
		TaotaoResult result = TaotaoResult.ok(tbItem);
		return result;
	}

	@RequestMapping(value = "/rest/item/query/item/desc/{id}")
	public TaotaoResult getItemDesc(@PathVariable("id") long itemId)
	{
		TbItemDesc tbItemDesc = itemService.getItemDescByItmeId(itemId);
		TaotaoResult result = TaotaoResult.ok(tbItemDesc);
		return result;
	}

}
