package com.PlaylistApplication;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@WebMvcTest(value = PlayListController.class, secure = false)
public class PlaylistApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlayListController playlistController;
	
    List<SongEntity> songList = new ArrayList<SongEntity>();
    SongEntity song = new SongEntity();
    PlayList mockPlayList;
    
    @Test
    public void getPlayListSongs() throws Exception {
    	songList.add(song);
    	mockPlayList = new PlayList("c6f9c78f8d1b6ae3f741c5b538000038","1-268907eacc9bc87bda0b8e84c2b24576","Playlist 1", "This is a test playlist", songList);
    	Mockito.when(
                        playlistController.getPlayListSongs(Mockito.anyString())).thenReturn(mockPlayList);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/songs")
    			.param("id", "c6f9c78f8d1b6ae3f741c5b538000038").accept(
        		MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
  
    	String expected = "{\"name\":\"Playlist 1\",\"description\":\"This is a test playlist\",\"songList\":[{\"title\":null,\"artist\":null,\"album\":null,\"releaseDate\":null,\"downloadDate\":null}],\"_id\":\"c6f9c78f8d1b6ae3f741c5b538000038\",\"_rev\":\"1-268907eacc9bc87bda0b8e84c2b24576\"}";  	
    	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    
    @Test
    public void createNewPlayList() throws Exception {
    	songList.add(song);
    	mockPlayList = new PlayList("c6f9c78f8d1b6ae3f741c5b538000038","1-268907eacc9bc87bda0b8e84c2b24576","Playlist 1", "This is a test playlist", songList);
    	
    	String examplePlaylistJSON = "{\"name\":\"Playlist 1\",\"description\":\"This is a test playlist\",\"songList\":[{\"title\":null,\"artist\":null,\"album\":null,\"releaseDate\":null,\"downloadDate\":null}],\"_id\":\"c6f9c78f8d1b6ae3f741c5b538000038\",\"_rev\":\"1-268907eacc9bc87bda0b8e84c2b24576\"}";
    	
    	Mockito.when(
                      playlistController.createNewPlayList(
                    		                                 Mockito.any(PlayList.class))).thenReturn(mockPlayList);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addNew")
    														  .accept(MediaType.APPLICATION_JSON).content(examplePlaylistJSON)
    														  .contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    	
    	MockHttpServletResponse response = result.getResponse();
    	
    	assertEquals(HttpStatus.OK.value(), response.getStatus());
    														  
    }
    
    @Test
    public void getAllPlaylists() throws Exception {
    	List<PlayList> playlist = new ArrayList<PlayList>();
    	songList.add(song);
    	mockPlayList = new PlayList("c6f9c78f8d1b6ae3f741c5b538000038","1-268907eacc9bc87bda0b8e84c2b24576","Playlist 1", "This is a test playlist", songList);
    	playlist.add(mockPlayList);
    	Mockito.when(
                        playlistController.getAllPlayLists()).thenReturn(playlist);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/playlist")
    			.accept(
        		MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    	
    	JSONArray array = new JSONArray(result.getResponse().getContentAsString());
   
  
    	String expected = "{\"name\":\"Playlist 1\",\"description\":\"This is a test playlist\",\"songList\":[{\"title\":null,\"artist\":null,\"album\":null,\"releaseDate\":null,\"downloadDate\":null}],\"_id\":\"c6f9c78f8d1b6ae3f741c5b538000038\",\"_rev\":\"1-268907eacc9bc87bda0b8e84c2b24576\"}";  	
    	JSONAssert.assertEquals(expected, array.getJSONObject(0), false);
    }

}
