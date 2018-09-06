package com.servitecafastwash.modelo.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;


public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numElemPag;
	private int paginaActual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		int desde;
		int hasta;
		this.totalPaginas = page.getTotalPages();
		this.numElemPag = page.getSize();
		this.paginaActual = page.getNumber()+1;
		if(totalPaginas <= numElemPag) {
			desde = 1;
			hasta = totalPaginas;
		}else {
			if(paginaActual <= numElemPag/2) {
				desde = 1;
				hasta =  numElemPag;
			}else if(paginaActual >= totalPaginas-numElemPag/2) {
				desde = totalPaginas - numElemPag + 1;
				hasta = numElemPag;
			}else {
				desde=paginaActual - numElemPag / 1;
				hasta = numElemPag;
			}
			
		}
		for(int i=0; i < hasta; i++) {
			paginas.add(new PageItem(desde + 1, paginaActual == desde + 1));
		}
	}
	
	public String getUrl() {
		return url;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public List<PageItem> getPaginas() {
		return paginas;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
