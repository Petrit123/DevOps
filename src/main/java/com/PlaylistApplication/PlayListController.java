package com.PlaylistApplication;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin(origins = "http://63.35.162.218:3000")
public class PlayListController {
	
	@Autowired
	PlayListRepository playListRepo;
	
	
	//Get all playlists
	@RequestMapping(value = "/playlist" , produces="application/json",method = RequestMethod.GET)
	public List<PlayList> getAllPlayLists(){
//		List<SongEntity> songList = new ArrayList<SongEntity>();
//		SongEntity song = new SongEntity();
//		songList.add(song);
//		PlayList andrewsPlaylist =  new PlayList("Andrew's playlist", "This is a test playlist", songList);
//		PlayList murtsPlaylist = new PlayList("Murt's playlist", "This is a test playlst", songList);
//		playListRepo.add(andrewsPlaylist);

		return playListRepo.getAll();
	}
	
	
	//Get playListSongs
	@RequestMapping(value = "/songs" , produces="application/json",method = RequestMethod.GET)
	public PlayList getPlayListSongs(@RequestParam String id){
		PlayList playList = playListRepo.get(id);
		return playList;
	}
		
	//Create a new playlist document
	@RequestMapping(value = "/addNew",method =RequestMethod.POST)
	public PlayList createNewPlayList(@RequestBody PlayList playList) {
		playListRepo.add(playList);
		return playList;
	}
	
	
	//Update a playlist
	@RequestMapping(value = "/update",produces = "application/json", method = RequestMethod.GET)
	public void updatePlayList(@RequestParam String name, String description,String id) {
		PlayList playList = playListRepo.get(id);
		playList.setName(name);
		playList.setDescription(description);
		playList.setRevision(playList.getRevision());
		playListRepo.update(playList);
	}
	

	//Delete a playlist document
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public void deletePlayList(@RequestParam String id) {
		PlayList playList = playListRepo.get(id);
		playListRepo.remove(playList);
	}
		
	//Update a playlist
	@RequestMapping(value = "/addNewSong",produces = "application/json", method = RequestMethod.GET)
	public void addSongList(@RequestParam String title, String artist, String album, String releaseDate, String downloadDate, String id) {
		PlayList playList = playListRepo.get(id);
		SongEntity songs = new SongEntity();
		songs.setTitle(title);
		songs.setArtist(artist);
		songs.setAlbum(album);
		songs.setReleaseDate(releaseDate);
		songs.setDownloadDate(downloadDate);
        List<SongEntity> songList = playList.getSongList();
        songList.add(songs);
        playList.setSongList(songList);
		playListRepo.update(playList);
	}

	@RequestMapping(value = "/deleteSong",produces = "application/json", method = RequestMethod.GET)
	public void deleteSongList(@RequestParam String id, int index) {
		PlayList playList = playListRepo.get(id);
        List<SongEntity> songList = playList.getSongList();
        songList.remove(index);
        playList.setSongList(songList);
		playListRepo.update(playList);
	}
	
	@RequestMapping(value = "/editSongs",produces = "application/json", method = RequestMethod.GET)
	public void updateSongList(@RequestParam String title, String artist, String album, String releaseDate, String downloadDate, String id, int index) {
		PlayList playList = playListRepo.get(id);
        List<SongEntity> songList = playList.getSongList();
		SongEntity songs = songList.get(index);
		songs.setTitle(title);
		songs.setArtist(artist);
		songs.setAlbum(album);
		songs.setReleaseDate(releaseDate);
		songs.setDownloadDate(downloadDate);
		songList.set(index, songs);
        playList.setSongList(songList);
		playListRepo.update(playList);
	}

	@RequestMapping(value = "/mapReduce", produces = "application/json", method = RequestMethod.GET)
	public int mapReduce(@RequestParam String playListName) {
		return playListRepo.getNoSongs(playListName);
	}
	


}