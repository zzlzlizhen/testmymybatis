package lz.utils;

import java.util.List;

public class DatatablesViewPage<T> {

	private List<T> aaData; //aaData 与datatales 加载的“dataSrc"对应
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private int sEcho;
	public DatatablesViewPage() {
		
	}
	public DatatablesViewPage(List<T> aaData, int iTotalDisplayRecords,
			int iTotalRecords, int sEcho) {
		super();
		this.aaData = aaData;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.iTotalRecords = iTotalRecords;
		this.sEcho = sEcho;
	}

	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	
}

