package com.omvl.omvl.repository;

import com.omvl.omvl.domain.Item;
import java.util.List;

public interface ItemRepository {

	Item findById(Long id);
	List<Item> findByType(String type);

}
