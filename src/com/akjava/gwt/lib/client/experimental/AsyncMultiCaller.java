package com.akjava.gwt.lib.client.experimental;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.Timer;

public  abstract class AsyncMultiCaller<T>{
		List<T> datas;
		private boolean cancelled;
		public boolean isCancelled() {
			return cancelled;
		}
		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}

		private boolean executing;
		 public AsyncMultiCaller(List<T> datas){
			 checkNotNull(datas,"AsyncMultiCaller:data is null");
			 checkState(datas.size()>0,"AsyncMultiCaller:data is empty");
			this.datas=Lists.newArrayList(datas);
		}
		public void done(T data,boolean success){
			datas.remove(data);
			
			if(success){
				onSuccess(data);
			}else{
				onFaild(data);
			}
			
			executing=false;
		}
		/**
		 * this become  bottle neck of perfomance when execute time less 50ms
		 */
		public void startCall(){
			startCall(50);//so so slow
		}
		int called;
		int skipRate=5;
		public void startCall(int wait){
			onStart();
			Timer timer=new Timer(){
				@Override
				public void run() {
					if(executing){
						return;
					}
					
					if(skipRate!=0){
					called++;
					if(called==skipRate-1){
						called=0;
						//LogUtils.log("skipped");
						return;//skip for garbage collection
					}
					}
					
					
					if(datas.size()>0 && !cancelled){
						executing=true;
						execAsync(datas.get(0));
					}else{
						executing=false;
						this.cancel();
						doFinally(cancelled);
					}
				}
			};
			timer.scheduleRepeating(wait);
		}
		
		public void onStart(){
			//LogUtils.log("size:"+datas.size());
		}
		
		public void onSuccess(T data){
			//LogUtils.log("success:"+data);
		}
		
		public void onFaild(T data){
			LogUtils.log("faild:"+data);
		}
		
		public abstract void doFinally(boolean cancelled);
		/*
		public void doFinally(boolean cancelled){
			if(cancelled)
			LogUtils.log("async multi-caller finally:cancelled="+cancelled);
		}*/
		
		/**
		 * must call public void done(T data,boolean success) from here
		 * @param data
		 */
		public abstract void execAsync(final T data);
	}