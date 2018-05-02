package io.sample.model;

public class Album {
    private String name;
    private int id;
    private int artistId;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getArtistId() {
        return artistId;
    }
    
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
