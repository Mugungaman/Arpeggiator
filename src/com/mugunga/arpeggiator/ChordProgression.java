package com.mugunga.arpeggiator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mugunga.musicmodels.Chord;


public class ChordProgression implements Iterable<Chord>{

	private List<Chord> chords = new ArrayList<>();
	
	public ChordProgression() {
		
	}
	
	public ChordProgression(List<Chord> chords) {
		for(Chord chord : chords)  {
			this.chords.add(chord);
		}
	}
	
	public void add(Chord chord) {
		chords.add(chord);
	}
	
	@Override
	public Iterator<Chord> iterator() {
		
		return chords.iterator();
	}
	public int size() {
		return chords.size();
	}
	
	public Chord getFirst(int index) {
		return chords.get(0);
	}
	
	public Chord get(int index) {
		if(index > 0) {
			return chords.get(index - 1);
		} else if(index < 0) {
			return chords.get(chords.size() + index);
		} else if (index == 0) {
			return chords.get(0);
		}
		return new Chord();
	}
}
