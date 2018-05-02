package io.sample;

import io.sample.model.Artist;
import io.sample.model.DataSource;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        DataSource dataSource = new DataSource();
        if (!dataSource.open()) {
            System.out.println("Can not open datasource");
            return;
        }
    
        List<Artist> artists = dataSource.queryArtists(5);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID =: " + artist.getId() + ",   Name =: " + artist.getName());
        }
    
        List<String> albumsForArtist = dataSource.queryAlbumsForArtist("Iron Maiden", DataSource.ORDER_BY_ASC);
        for (String album : albumsForArtist) {
            System.out.println(album);
        }
        dataSource.close();
    }
}
