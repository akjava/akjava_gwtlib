package com.akjava.gwt.lib.client.experimental;

import java.util.ArrayList;
import java.util.List;

import com.akjava.gwt.lib.client.CanvasUtils;
import com.akjava.lib.common.graphics.Rect;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RectListEditor extends VerticalPanel implements LeafValueEditor<List<Rect>>{
	
	private AreaSelectionControler areaControler;

	private List<Rect> rectangles=new ArrayList<Rect>();
	
	private HorizontalPanel optionButtonContainer;//for future expand
	public HorizontalPanel getOptionButtonContainer() {
		return optionButtonContainer;
	}
	
	public Canvas getCanvas(){
		return areaControler.getCanvas();
	}
	
	public void doPlus(){
		if(!areaControler.getSelectionRect().hasWidthAndHeight()){
			return;
		}
		
		if(!rectangles.contains(areaControler.getSelectionRect())){
			rectangles.add(areaControler.getSelectionRect().copy());
		}
		areaControler.getSelectionRect().clear();
		
		areaControler.updateRect();
	}
	
	public RectListEditor(){
		HorizontalPanel buttons=new HorizontalPanel();
		buttons.setSpacing(4);
		add(buttons);
		Button plusBt=new Button("+",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doPlus();
				//bugs it's gone if exist same rect
				//need size
				
			}
		});
		buttons.add(plusBt);
		plusBt.setWidth("80px");
		Button minusBt=new Button("-",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//bugs same size is gone
				rectangles.remove(areaControler.getSelectionRect());
				if(rectangles.size()>0){
					rectangles.get(rectangles.size()-1).copyTo(areaControler.getSelectionRect());
					rectangles.remove(rectangles.size()-1);
				}else{
					areaControler.getSelectionRect().clear();
				}
				areaControler.updateRect();
			}
		});
		buttons.add(minusBt);
		minusBt.setWidth("80px");
		optionButtonContainer=new HorizontalPanel();
		buttons.add(optionButtonContainer);
		areaControler = new AreaSelectionControler(){
			@Override
			public void drawExtra(Canvas canvas){
				if(areaControler.getSelectionRect().hasWidthAndHeight()){
					canvas.getContext2d().setStrokeStyle("#fff");
				}else{
					canvas.getContext2d().setStrokeStyle("#888");
				}
				
				for(Rect rect:rectangles){
					if(!rect.equals(areaControler.getSelectionRect())){
						canvas.getContext2d().strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
					}
				}
			}
			@Override
			public boolean canContinueStart(int x,int y){
				for(Rect rect:rectangles){
					if(rect.contains(x, y)){
						if(areaControler.getSelectionRect().hasWidthAndHeight()){
							rectangles.add(areaControler.getSelectionRect().copy());
						}
						
						rect.copyTo(areaControler.getSelectionRect());
						rectangles.remove(rect);
						updateRect();
						return false;
					}
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
	public void setValue(List<Rect> value) {
		rectangles.clear();
		
		if(value==null){
			value=new ArrayList<Rect>();
		}
		
		if(value.size()>0){
			value.get(0).copyTo(areaControler.getSelectionRect());
		}else{
			areaControler.getSelectionRect().clear();
		}
		for(int i=1;i<value.size();i++){
			rectangles.add(value.get(i).copy());
		}
		areaControler.updateRect();
	}

	@Override
	public List<Rect> getValue() {
		List<Rect> rects=new ArrayList<Rect>();
		for(Rect r:rectangles){
			rects.add(r.copy());
		}
		if(areaControler.getSelectionRect().hasWidthAndHeight()){
			rects.add(areaControler.getSelectionRect().copy());
		}
		return rects;
	}
	
}