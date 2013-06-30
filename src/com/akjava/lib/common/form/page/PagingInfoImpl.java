package com.akjava.lib.common.form.page;


public class PagingInfoImpl implements PagingInfo {
private int currentPage=1;
private long maxItem;
private int itemSize;

/**
 * 
 * @param currentPage
 * PageMenuUtil helop it
 * int page=PageMenuUtil.parsePageNumber(request, "page");
 * @param maxItem
 * get from query 
	public long count(){
		Query query=manager.newQuery("select count(this) from com.akjava.gwt.qualification.server.Qualification");
		Long value=(Long) query.execute();
		if(value!=null){
			return value;
		}else{
			return 0;
		}
		
	}
 * @param itemSize
you can define it.use it
public  List<Qualification> getDatas(long start,long size,String key,boolean isAsce){
	Query query =manager.newQuery(Qualification.class);
	if(key!=null){
		String order=key;
		if(!isAsce){
		order+=" desc";
		}
		query.setOrdering(order);
	}
	query.setRange(start, start+2);
	@SuppressWarnings("unchecked")
	List<Qualification> result=(List<Qualification>) query.execute();
	return result;
	}
 */

public PagingInfoImpl(int currentPage,long maxItem,int itemSize){
	this.currentPage=currentPage;
	this.maxItem=maxItem;
	this.itemSize=itemSize;
}

/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#getCurrentPage()
 */
@Override
public int getCurrentPageNumber() {
	return currentPage;
}
/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#setCurrentPage(int)
 */
@Override
public void setCurrentPageNumber(int currentPage) {
	this.currentPage = currentPage;
}
/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#getMaxItem()
 */
@Override
public long getCountItem() {
	return maxItem;
}
/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#setMaxItem(int)
 */
@Override
public void setCountItem(long maxItem) {
	this.maxItem = maxItem;
}
/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#getItemSize()
 */
@Override
public int getShowSizeInPage() {
	return itemSize;
}
/* (non-Javadoc)
 * @see com.akjava.gwt.qualification.server.page.PagingInfo#setItemSize(int)
 */
@Override
public void setShowSizeInPage(int itemSize) {
	this.itemSize = itemSize;
}
}
