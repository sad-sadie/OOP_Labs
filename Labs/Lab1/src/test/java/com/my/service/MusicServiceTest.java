package com.my.service;

import com.my.entity.Track;
import com.my.service.MusicService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.my.entity.Genre.*;
import static com.my.entity.Genre.ALTERNATIVE;
import static org.junit.Assert.*;

public class MusicServiceTest {

    private List<Track> tracks = new ArrayList<>();

    @Before
    public void setUp() {
        tracks.add(new Track("Linkin Park - In The End", ROCK, 219));
        tracks.add(new Track("Ke$ha - Tik ToK", POP, 200));
        tracks.add(new Track("Avicii - Levels", ELECTRONIC, 338));
        tracks.add(new Track("Green Day - Boulevard of Broken Dreams", ROCK, 260));
        tracks.add(new Track("Katy Perry - HOT N COLD", POP, 284));
        tracks.add(new Track("Kendrick Lamar - HUMBLE.", RAP, 184));
        tracks.add(new Track("twenty one pilots - heathens", ALTERNATIVE, 195));
        tracks.add(new Track("David Guetta - Memories", ELECTRONIC, 208));
        tracks.add(new Track("Juice WRLD - Lucid Dreams", RAP, 231));
        tracks.add(new Track("Evanescence - Bring Me to Life", ROCK, 254));
        tracks.add(new Track("Beethoven - Moonlight Sonata", CLASSIC, 900));
        tracks.add(new Track("xxxtentacion - Sad!", RAP, 167));
        tracks.add(new Track("Mozart - Requiem", CLASSIC, 275));
        tracks.add(new Track("Gotye - Somebody That I Used to Know", ALTERNATIVE, 245));
    }

    @Test
    public void testGetTracks() {
        assertEquals(tracks, new MusicService(tracks).getTracks());
    }

    @Test
    public void testGetTotalDuration() {
        assertEquals(3960, new MusicService(tracks).getTotalDuration());
    }

    @Test
    public void testSortByGenre() {
        MusicService musicService = new MusicService(new ArrayList<>(tracks));
        List<Track> expected = new ArrayList<>();
        expected.add(new Track("Linkin Park - In The End", ROCK, 219));
        expected.add(new Track("Green Day - Boulevard of Broken Dreams", ROCK, 260));
        expected.add(new Track("Evanescence - Bring Me to Life", ROCK, 254));
        expected.add(new Track("Ke$ha - Tik ToK", POP, 200));
        expected.add(new Track("Katy Perry - HOT N COLD", POP, 284));
        expected.add(new Track("Beethoven - Moonlight Sonata", CLASSIC, 900));
        expected.add(new Track("Mozart - Requiem", CLASSIC, 275));
        expected.add(new Track("Avicii - Levels", ELECTRONIC, 338));
        expected.add(new Track("David Guetta - Memories", ELECTRONIC, 208));
        expected.add(new Track("Kendrick Lamar - HUMBLE.", RAP, 184));
        expected.add(new Track("Juice WRLD - Lucid Dreams", RAP, 231));
        expected.add(new Track("xxxtentacion - Sad!", RAP, 167));
        expected.add(new Track("twenty one pilots - heathens", ALTERNATIVE, 195));
        expected.add(new Track("Gotye - Somebody That I Used to Know", ALTERNATIVE, 245));
        List<Track> actual = musicService.sortByGenre();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindByRange() {
        MusicService musicService = new MusicService(new ArrayList<>(tracks));
        List<Track> expected = new ArrayList<>();
        expected.add(new Track("Linkin Park - In The End", ROCK, 219));
        expected.add(new Track("Ke$ha - Tik ToK", POP, 200));
        expected.add(new Track("David Guetta - Memories", ELECTRONIC, 208));
        List<Track> actual = musicService.findByRange(200, 220);
        assertEquals(expected, actual);
    }

}
