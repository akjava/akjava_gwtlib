package com.akjava.lib.common.form;

import com.akjava.lib.common.form.FormFieldData;
import com.google.common.base.Predicate;

public class FormFieldDataPredicates {
	
	public static NotAutoCreate getNotAutoCreate(){
		return NotAutoCreate.INSTANCE;
	}
	
	public enum NotAutoCreate implements Predicate<FormFieldData>{
		INSTANCE;
		@Override
		public boolean apply(FormFieldData data) {
			return !data.isCreateAuto();
		}
	}
}
