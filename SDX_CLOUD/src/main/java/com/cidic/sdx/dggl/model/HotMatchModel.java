package com.cidic.sdx.dggl.model;

public class HotMatchModel {
	
	private int matchlistId;
	private int userId;
	private int countLike;
	private int innerClothId;
	private int outClothId;
	private int trousersId;
	private String modelurl;
	
	private CostumeModel innerClothUrl;
	private CostumeModel outClothUrl;
	private CostumeModel trousersClothUrl;
	
	public int getMatchlistId() {
		return matchlistId;
	}
	public void setMatchlistId(int matchlistId) {
		this.matchlistId = matchlistId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCountLike() {
		return countLike;
	}
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	public int getInnerClothId() {
		return innerClothId;
	}
	public void setInnerClothId(int innerClothId) {
		this.innerClothId = innerClothId;
	}
	public int getOutClothId() {
		return outClothId;
	}
	public void setOutClothId(int outClothId) {
		this.outClothId = outClothId;
	}
	public int getTrousersId() {
		return trousersId;
	}
	public void setTrousersId(int trousersId) {
		this.trousersId = trousersId;
	}
	
	public String getModelurl() {
		return modelurl;
	}
	public void setModelurl(String modelurl) {
		this.modelurl = modelurl;
	}
	public CostumeModel getInnerClothUrl() {
		return innerClothUrl;
	}
	public void setInnerClothUrl(CostumeModel innerClothUrl) {
		this.innerClothUrl = innerClothUrl;
	}
	public CostumeModel getOutClothUrl() {
		return outClothUrl;
	}
	public void setOutClothUrl(CostumeModel outClothUrl) {
		this.outClothUrl = outClothUrl;
	}
	public CostumeModel getTrousersClothUrl() {
		return trousersClothUrl;
	}
	public void setTrousersClothUrl(CostumeModel trousersClothUrl) {
		this.trousersClothUrl = trousersClothUrl;
	}
	
	
	
}
