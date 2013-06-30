package com.akjava.lib.common.form.page;

/**
 * TODO class with PagingInfoImpl
 * @author aki
 *
 */
public interface PagingInfo {

	public abstract int getCurrentPageNumber();

	public abstract void setCurrentPageNumber(int currentPage);

	public abstract long getCountItem();

	public abstract void setCountItem(long maxItem);

	public abstract int getShowSizeInPage();

	public abstract void setShowSizeInPage(int itemSize);

}