package com.ssafy.util;

public class PageInfo {
	
	private String url;
	private boolean isForward;
	
	public String getUrl() {
		return url;
	}

	public boolean isForward() {
		return isForward;
	}

	public PageInfo(String url, boolean isForward) {
		super();
		this.url = url;
		this.isForward = isForward;
	}
	
}
