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
	public static HaveLabelAndValue getHaveLabelAndValue(){
		return HaveLabelAndValue.INSTANCE;
	}
	public enum HaveLabelAndValue implements Predicate<FormFieldData>{
		INSTANCE;
		@Override
		public boolean apply(FormFieldData data) {
			return data.getType()==FormFieldData.TYPE_CHECK||data.getType()==FormFieldData.TYPE_SELECT_SINGLE||data.getType()==FormFieldData.TYPE_SELECT_MULTI;
		}
	}
}
