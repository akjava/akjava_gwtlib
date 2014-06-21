package com.akjava.gwt.lib.client.experimental;

import java.util.List;

import com.akjava.gwt.lib.client.LogUtils;

public  abstract class AsyncMultiCaller<T>{
		List<T> datas;
		private boolean cancelled;
		public AsyncMultiCaller(List<T> datas){
			this.datas=datas;
		}
		public void done(T data,boolean success){
			datas.remove(data);
			if(success){
				onSuccess(data);
			}else{
				onFaild(data);
			}
			if(datas.size()>0 && !cancelled){
				execAsync(datas.get(0));
			}else{
				doFinally(cancelled);
			}
		}
		public void startCall(){
			onStart();
			execAsync(datas.get(0));
		}
		
		public void onStart(){
			LogUtils.log("size:"+datas.size());
		}
		
		public void onSuccess(T data){
			LogUtils.log("success:"+data);
		}
		
		public void onFaild(T data){
			LogUtils.log("faild:"+data);
		}
		
		public void doFinally(boolean cancelled){
			LogUtils.log("finally:cancelled="+cancelled);
		}
		
		/**
		 * must call public void done(T data,boolean success) from here
		 * @param data
		 */
		public abstract void execAsync(final T data);
	}