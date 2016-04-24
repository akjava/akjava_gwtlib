package com.akjava.gwt.lib.client.datalist;

import com.akjava.gwt.lib.client.StorageDataList;


/**
 * for basic clear & dump & restore
 * @author aki
 *
 */
public interface SimpleTextDatasOwner {
	public StorageDataList getStorageDataList();
	public void initializeListData();
}
