package com.PlaylistApplication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class PlayList {
	    
		@JsonProperty("_id")	
	    private String id;
	    @JsonProperty("_rev")
	    private String revision;
	    private String name;
	    private String description;
	    private List<SongEntity> songList = new ArrayList<SongEntity>();
	    
	    public PlayList(String id, String revision, String name, String description, List<SongEntity> songList) {
		this.id = id;
		this.revision = revision;
		this.name = name;
		this.description = description;
		this.songList = songList;
	}

	    public PlayList( String name, String description, List<SongEntity> songList) {
		this.name = name;
		this.description = description;
		this.songList = songList;
	}

	    public PlayList() {
	    	
	    }
	    
	    public String getId() {
	        return id;
	    }
	    
	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getRevision() {
	        return revision;
	    }

	    public void setRevision(String revision) {
	        this.revision = revision;
	    }
	        
	    public String getName() {
	        return name;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<SongEntity> getSongList() {
			return songList;
		}

		public void setSongList(List<SongEntity> songList) {
			this.songList = songList;
		}

}