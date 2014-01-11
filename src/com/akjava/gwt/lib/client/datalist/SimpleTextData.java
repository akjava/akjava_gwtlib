package com.akjava.gwt.lib.client.datalist;

import com.akjava.lib.common.utils.ValuesUtils;

/**
 * In future replace SimpleTextData
 * @author aki
 *
 */
public class SimpleTextData {
private int id;
private long cdate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getCdate() {
	return cdate;
}
public void setCdate(long cdate) {
	this.cdate = cdate;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
private String name;
private String data;
private static long toCdate(String cdate){
	long c=0;
	if(cdate==null ||cdate.isEmpty()){
		c=System.currentTimeMillis();
	}else{
		c=ValuesUtils.toLong(cdate, 0);
	}
	return c;
}
public SimpleTextData(int id,String name,String data,String cdate){
	this(id,name,data,toCdate(cdate));
}
public SimpleTextData(int id,String name,String data,long cdate){
	this.id=id;
	this.name=name;
	this.data=data;
	this.cdate=cdate;
}
public SimpleTextData(int id,String name,String data){
	this(id,name,data,System.currentTimeMillis());
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (cdate ^ (cdate >>> 32));
	result = prime * result + ((data == null) ? 0 : data.hashCode());
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	SimpleTextData other = (SimpleTextData) obj;
	if (cdate != other.cdate)
		return false;
	if (data == null) {
		if (other.data != null)
			return false;
	} else if (!data.equals(other.data))
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}



}
