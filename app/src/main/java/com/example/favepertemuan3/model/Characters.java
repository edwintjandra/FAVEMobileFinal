package com.example.favepertemuan3.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Characters{

	@SerializedName("favorites")
	private int favorites;

	@SerializedName("images")
	private Images images;

	@SerializedName("name_kanji")
	private String nameKanji;

	@SerializedName("name")
	private String name;

	@SerializedName("about")
	private String about;

	@SerializedName("mal_id")
	private int malId;

	@SerializedName("nicknames")
	private List<String> nicknames;

	@SerializedName("url")
	private String url;

	public int getFavorites(){
		return favorites;
	}

	public Images getImages(){
		return images;
	}

	public String getNameKanji(){
		return nameKanji;
	}

	public String getName(){
		return name;
	}

	public String getAbout(){
		return about;
	}

	public int getMalId(){
		return malId;
	}

	public List<String> getNicknames(){
		return nicknames;
	}

	public String getUrl(){
		return url;
	}
}