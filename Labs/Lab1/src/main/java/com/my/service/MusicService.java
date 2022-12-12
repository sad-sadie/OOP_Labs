package com.my.service;


import com.my.entity.Track;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MusicService {

    List<Track> tracks;

    public MusicService(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public int getTotalDuration() {
        return tracks.stream().mapToInt(Track::getDuration).sum();
    }

    public List<Track> sortByGenre() {
        tracks.sort(Comparator.comparing(Track::getGenre));
        return tracks;
    }

    public List<Track> findByRange(int from, int to) {
        System.out.println("\nTracks, which durations is between "
                +  from / 60 + " minutes " + from % 60 + " seconds and "
                + to / 60 + " minutes " + to % 60 + " seconds");

        return tracks.stream()
                .filter(track -> track.getDuration() >= from && track.getDuration() <= to)
                .collect(Collectors.toList());
    }
}
