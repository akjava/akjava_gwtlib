package com.akjava.gwt.lib.client.experimental;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.lib.common.graphics.IntRect;
import com.google.common.collect.Lists;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RectListEditor extends VerticalPanel implements LeafValueEditor<List<IntRect>>{
	
	public interface RectListListener{
		public void onRectRemoved(IntRect rect);
		public void onRectSelected(IntRect rect);
		//public void onRectAdded(Rect rect);
	}
	private List<RectListListener> listeners=Lists.newArrayList();
	public void addListener(RectListListener listener){
		listeners.add(listener);
	}
	public void removeListener(RectListListener listener){
		listeners.remove(listener);
	}
	private void fireOnRectRemoved(IntRect rect){
		for(RectListListener listener:listeners){
			listener.onRectRemoved(rect);
		}
	}
	private void fireOnRectSelected(IntRect rect){
		for(RectListListener listener:listeners){
			listener.onRectSelected(rect);
		}
	}
	
	
	private AreaSelectionControler areaControler;

	private List<IntRect> rectangles=new ArrayList<IntRect>();
	private IntRect selectionRect;
	public IntRect getSelectionRect() {
		return selectionRect;
	}

	private HorizontalPanel optionButtonContainer;//for future expand
	public HorizontalPanel getOptionButtonContainer() {
		return optionButtonContainer;
	}
	
	public Canvas getCanvas(){
		return areaControler.getCanvas();
	}
	
	private boolean readOnly;

	private HorizontalPanel plusMinusButtons;
	
	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		plusMinusButtons.setVisible(!readOnly);
		areaControler.setNoNeedChangeCursor(readOnly);
	}

	public void doPlus(){
		if(!areaControler.getSelectionRect().hasWidthAndHeight()){//no need to add
			return;
		}
		
		//update current selection
		syncSelectionFromAreaControler();
		
		IntRect rect=new IntRect();
		rectangles.add(rect);
		
		setAndCopyToSelection(rect);
		
		
		areaControler.updateRect();
		getCanvas().setFocus(true);
	}
	public void doMinus(){
		//bugs same size is gone
		rectangles.remove(selectionRect);
		fireOnRectRemoved(selectionRect);
		
		if(rectangles.size()>0){
			setAndCopyToSelection(rectangles.get(rectangles.size()-1));//last one
			
		}else{
			IntRect rect=new IntRect();
			rectangles.add(rect);
			
			setAndCopyToSelection(rect);
		}
		areaControler.updateRect();
		getCanvas().setFocus(true);
	}
	private void syncSelectionFromAreaControler(){
		areaControler.getSelectionRect().copyTo(selectionRect);
	}
	
	public void selectNext(){
		if(selectionRect==null){
			return;
		}
		
		if(rectangles.size()<2){//no need
			return;
		}
		
		int index=rectangles.indexOf(selectionRect);
		if(index==-1){//somehow invalid data
			return;
		}
		
		index++;
		if(index>=rectangles.size()){
			index=0;
		}
		
		setAndCopyToSelection(rectangles.get(index));
		areaControler.updateRect();
	}
	
	public void selectPrev(){
		if(selectionRect==null){
			return;
		}
		
		if(rectangles.size()<2){//no need
			return;
		}
		
		int index=rectangles.indexOf(selectionRect);
		if(index==-1){//somehow invalid data
			return;
		}
		
		index--;
		if(index<0){
			index=rectangles.size()-1;
		}
		
		setAndCopyToSelection(rectangles.get(index));
		areaControler.updateRect();
	}
	
	
	private void setAndCopyToSelection(IntRect rect){
		selectionRect=rect;
		selectionRect.copyTo(areaControler.getSelectionRect());
		fireOnRectSelected(selectionRect);
	}
	
	protected  boolean isAreaControlerSelectionRectHashWidthAndHeight(){
		return areaControler.getSelectionRect().hasWidthAndHeight();
	}
	protected List<String> whiteColor=Lists.newArrayList("#fff");
	protected List<String> grayColor=Lists.newArrayList("#888");
	public List<String> getRectColors(IntRect rect){
		if(isAreaControlerSelectionRectHashWidthAndHeight()){
			return whiteColor;
		}else{
			return grayColor;
		}
	}
	public RectListEditor(){
		plusMinusButtons = new HorizontalPanel();
		plusMinusButtons.setSpacing(4);
		add(plusMinusButtons);
		Button plusBt=new Button("+",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doPlus();
				//bugs it's gone if exist same rect
				//need size
				
			}
		});
		plusMinusButtons.add(plusBt);
		plusBt.setWidth("80px");
		Button minusBt=new Button("-",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doMinus();
			}
		});
		plusMinusButtons.add(minusBt);
		minusBt.setWidth("80px");
		optionButtonContainer=new HorizontalPanel();
		plusMinusButtons.add(optionButtonContainer);
		areaControler = new AreaSelectionControler(){
			@Override
			public void drawExtra(Canvas canvas){
				int csize=4;
				
				for(IntRect rect:rectangles){
					if(!readOnly && selectionRect==rect){
						continue;
					}
					
					
					List<String> colors=getRectColors(rect);
					for(int i=0;i<colors.size();i++){
						canvas.getContext2d().setStrokeStyle(colors.get(i));
						canvas.getContext2d().strokeRect(rect.getX()-i*csize, rect.getY()-i*csize, rect.getWidth()+i*2*csize, rect.getHeight()+i*2*csize);
					}
				}
			}
			@Override
			public boolean canContinueStart(int x,int y){
				IntRect closest=null;
				int length=0;
				for(IntRect rect:rectangles){//check all rect and find 
					if(rect!=selectionRect && rect.contains(x, y)){
						/*I'm not sure sync only this situation
						if(areaControler.getSelectionRect().hasWidthAndHeight()){
							//sync selection
						}*/
						
						
						int leng=Math.abs(rect.getX()+rect.getWidth()/2-x)+Math.abs(rect.getY()+rect.getHeight()/2-y);
						if(closest==null || leng<length){
							closest=rect;
							length=leng;
						}
						
					}
					
				}
				if(closest!=null){
					syncSelectionFromAreaControler();
					
					setAndCopyToSelection(closest);//switch to rect
					
					updateRect();
					return false;
				}
				
				if(readOnly){//can't edit
					return false;
				}
				
				return true;
			}
		};
		add(areaControler.getCanvas());
		
		
	}
	public void setBackgroundImage(ImageElement imageElement){
	
			CanvasUtils.setSize(areaControler.getCanvas(), imageElement.getWidth(), imageElement.getHeight());
			areaControler.getCanvas().setVisible(true);
			
			areaControler.setSpace(imageElement.getWidth(), imageElement.getHeight());
			
			CanvasUtils.setBackgroundImage(areaControler.getCanvas(), imageElement.getSrc());
			areaControler.updateRect();
		
	}
	
	public void clearBackgroundImage(){
		CanvasUtils.clearBackgroundImage(areaControler.getCanvas());
	}
	
	@Override
	public void setValue(List<IntRect> value) {
		rectangles.clear();
		selectionRect=null;
		
		if(value==null){
			value=new ArrayList<IntRect>();
		}
		for(IntRect rect:value){
			if(rect.hasWidthAndHeight()){
				rectangles.add(rect);
			}
		}
		
		if(rectangles.size()==0){
			IntRect rect=new IntRect();//same as clear rect
			rectangles.add(rect);
		}
		
		setAndCopyToSelection(rectangles.get(0));
		
		areaControler.updateRect();//this called before labels set
	}

	@Override
	public List<IntRect> getValue() {
		List<IntRect> rects=new ArrayList<IntRect>();
		
		syncSelectionFromAreaControler();
		
		//only return valid rect
		for(IntRect r:rectangles){
			if(r.hasWidthAndHeight()){
				//rects.add(r.copy());//stop copying reduce construct time and for mapping
				rects.add(r);
			}
		}
		
		return rects;
	}
	public void updateCanvas() {
		areaControler.updateRect();
	}
	
}