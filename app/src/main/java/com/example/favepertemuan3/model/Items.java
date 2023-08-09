package com.example.favepertemuan3.model;

import com.google.gson.annotations.SerializedName;

public class Items{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("count")
	private int count;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public int getCount(){
		return count;
	}
}