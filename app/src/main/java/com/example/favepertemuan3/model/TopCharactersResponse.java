package com.example.favepertemuan3.model;

import java.util.Collection;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopCharactersResponse{

	@SerializedName("pagination")
	private Pagination pagination;

	@SerializedName("data")
	private List<Characters> charachters;

	public Pagination getPagination(){
		return pagination;
	}
	public Collection<? extends Characters> getCharacters(){
		return charachters;
	}
 }