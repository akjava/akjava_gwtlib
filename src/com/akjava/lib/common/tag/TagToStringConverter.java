package com.akjava.lib.common.tag;

public class TagToStringConverter {

	public static String convert(Tag tag){
		Tag root=tag;
		StringBuffer buffer=new StringBuffer();
		
		append(root,tag,buffer);
		
		return buffer.toString();
	}
	private static void append(Tag root,Tag tag,StringBuffer buffer){
		int depth=calculateLevel(root, tag);
		buffer.append(createSpace(depth));
		buffer.append(tag.getStartTagText());
		if(tag.isSingleTag()){
			buffer.append("\n");
		}else{
			boolean shouldraw=false;
			String text=tag.getText();
			if(text!=null){
				buffer.append(text);
				shouldraw=true;
			}
			
			if(tag.getChildrens().size()==0){
				shouldraw=true;
			}else{
				if(!shouldraw){
					buffer.append("\n");
				}
			}
			
			for(Tag child:tag.getChildrens()){
				append(root,child,buffer);
			}
			if(shouldraw){
				buffer.append(tag.getEndTagText());
				buffer.append("\n");
			}else{
				buffer.append(createSpace(depth));
				buffer.append(tag.getEndTagText());
				buffer.append("\n");
			}
			
		}
		
	}
	private static String createSpace(int depth){
		String ret="";
		for(int i=0;i<depth;i++){
			ret+="\t";
		}
		return ret;
	}
	private static int calculateLevel(Tag root,Tag tag){
		int depth=0;
		Tag current=tag;
		while(current.getParent()!=null||current.getParent()==root){
			depth++;
			current=current.getParent();
		}
		return depth;
	}
}

