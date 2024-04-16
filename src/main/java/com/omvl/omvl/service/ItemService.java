package com.omvl.omvl.service;

import com.omvl.omvl.domain.Item;
import com.omvl.omvl.repository.ItemRepository;
import java.util.List;

public class ItemService {

	private final ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	/**
	 * 아이템 상세보기
	 */
	public Item showDetail(Long id) {
		return itemRepository.findById(id);
	}

	/**
	 * 타입별 아이템 가져오기
	 */
	public List<Item> showItems(String type) {
		return itemRepository.findByType(type);
	}
}
