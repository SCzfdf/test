package bak;

public class ResponseData<E> {
	private Integer code;
	private E data [];
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public E[] getData() {
		return data;
	}
	public void setData(E[] data) {
		this.data = data;
	}
	
}
