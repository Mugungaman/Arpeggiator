package com.mugunga.arpeggiator;

import com.mugunga.counterpoint.NoteMelody;
import com.mugunga.counterpoint.NoteMelodyInProgress;
import com.mugunga.musicmodels.Chord;


public class Arpeggiator {
	Chord tonicChord;
	ChordSystem system;
	NoteMelodyInProgress melody;		
	ChordProgression chordProg;
	final public int NOTES_PER_CHORD = 6;
	private int[] arpPattern = {0, 1, 2, 3, 4, 2, 3, 4};
	
	public Arpeggiator(ChordSystem system) {
		l("Arp constructor...");
		this.system = system;
		tonicChord = system.firstChord();
		l("" + tonicChord);
	}
	
	public NoteMelody arpeggiate(int numChords) {
		melody = new NoteMelodyInProgress();
		chordProg = new ChordProgression();
		//first Chord
		
		for(int i = 1; i <= numChords; i++) {
			l("i is:" + i);
			Chord chord = determineNextChord();
			if(null == chord) {
				l("null chord");
			}
			chordProg.add(chord);
			l(" next chord is: " + chord.toString());
			//arpeggiateChord();
		}
		return melody;
	}
	
	private Chord determineNextChord() {
//		l("chord");
		if(chordProg.size() > 0) {
			l("getting randomized chord for " + chordProg.toString() + " prog size:" + chordProg.size());
			return system.randomNextChord(lastChord());
		} else {
			l("returning tonicChord;");
			return tonicChord;
		}
	}

	private Chord lastChord() {
		return chordProg.get(chordProg.size() - 1);
	}
	
	private void l(String msg) {
		System.out.println("Arp Log         :" + msg);
		
	}
}
