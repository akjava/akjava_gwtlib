package com.akjava.lib.common.form.page;

import java.util.HashMap;
import java.util.Map;

import com.akjava.lib.common.tag.Tag;

public class PageMenuHtmlMaker {
	private String pagesClass="menu_pages";
	private String currentClass="menu_current";
	private String pageKey="page";
	private String baseQuery;
	private String allDivClass="menu_all";
	private String pageSpanClass="menu_page";
	private String descriptionDivClass="menu_description";
	//TODO something message
	private String description="$start件目から$end件目を表示しています";
	private String empty="該当データはありません";
	private String baseUrl="";
	public PageMenuHtmlMaker(String baseUrl,String basicQuery){
		this.baseUrl=baseUrl;
		this.baseQuery=basicQuery;
	}
	
	public String createPageMenuHtml(PagingInfo info){
		Tag allDiv=new Tag("div");
		//Div allDiv=new Div();
		allDiv.setAttribute("class", allDivClass);
		
		
		int start=(info.getCurrentPageNumber()-1)*info.getShowSizeInPage();
		int end=start+info.getShowSizeInPage();
		start++;
		int maxPage=(int) (info.getCountItem()/info.getShowSizeInPage());
		if(info.getCountItem()%info.getShowSizeInPage()>0){
			maxPage++;
		}
		
		String des=description.replace("$start", ""+start);
		long countItem=info.getCountItem();
		des=des.replace("$end", ""+Math.min(end,info.getCountItem()));
		if(countItem==0){
			des=empty;
			}
		Tag desc=new Tag("div");
		desc.setText(des);
		desc.setAttribute("class", descriptionDivClass);
		
		allDiv.addChild(desc);
		
		Tag numdiv=new Tag("div");
		numdiv.setAttribute("class",pagesClass);
		allDiv.addChild(numdiv);
		
		for(int i=1;i<=maxPage;i++){
			if(i==info.getCurrentPageNumber()){
				Tag span=new Tag("span");
				span.setAttribute("class",currentClass);
				span.setText("["+i+"]");
				numdiv.addChild(span);
			}else{
				Map<String,String> replaced=new HashMap<String, String>();
				replaced.put(pageKey,""+i+"");
				String query=QueryUtils.replace(baseQuery, replaced);
				Tag sp=new Tag("span");
				sp.setAttribute("class",pageSpanClass);
				Tag alink=new Tag("a");
				alink.setAttribute("href", baseUrl+"?"+query);
				
				alink.setText("["+i+"]");
				sp.addChild(alink);
				numdiv.addChild(sp);
			}
		}
		
		
		return allDiv.toString();
	}
}
