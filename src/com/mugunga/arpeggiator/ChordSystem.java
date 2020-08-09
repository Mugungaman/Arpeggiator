package com.mugunga.arpeggiator;
import java.util.HashMap;
import java.util.Map;

import com.mugunga.musicmodels.Chord;

public class ChordSystem {
	Map<String, ChordFreq> chordFreq = new HashMap<>();
	Map<String, ChordFreq> chordPrecede = new HashMap<>();
	ChordProgression chordProg;
	
	public ChordSystem(ChordProgression chordProg) {
		this.chordProg = chordProg;
		generateFrequencyMap();
	}

	private void generateFrequencyMap() {
		Map<String, ChordFreq> chordFreq = new HashMap<>();
		int c = 0;
		Chord lastChord = null;
		for(Chord chord : chordProg) {
			c++;
			log("processing chord: " + chord + " :: " + c);
			if(c>1) {
				if(!chordFreq.containsKey(lastChord)){
					log(" add Chord Follow Freq for " + lastChord.toString() + " to map");
					chordFreq.put(lastChord.toString(), new ChordFreq(lastChord));
					
				} else {
					log("chordStructure already contains map for chord");
					
				}
				
				chordFreq.get(lastChord).addChordOccurance(chord, 1);
				chordFreq.get(lastChord).print();
				log("chordStructure size" + chordFreq.size());
				
			}
			lastChord =  chord;
			
			//chord.print();
		}
		this.chordFreq = chordFreq;
		
	}
	
	public Chord firstChord() {
		return chordProg.get(0);
	}
	
	private void log(String msg) {
		System.out.println("Chord System Log:      " + msg);
	}

	public Chord randomNextChord(Chord previousChord) {
		//TODO Chord Progression needs a tpString method
		log("get random chord :" + previousChord.toString());
		//TODO: chord freq returning null
		log("chordFreq: " + printChordMap(chordFreq));
		if(chordFreq.containsKey(previousChord)) {
			log("do I contain previous chord?");
			return chordFreq.get(previousChord).getRandomNextChord();
		} else {
			return null;
		}
		
		
	}

	private String printChordMap(Map<String, ChordFreq> chordFreq) {
		for (Map.Entry<String, ChordFreq> entry : chordFreq.entrySet()) {
			l("Chord " + entry.getKey() + ":::");
			entry.getValue().print();
			
		}
		l("---");
		
		return null;
	}
	
	private void l(String msg) {
		System.out.println("ChordFollowFreqLog:   " + msg);
	}
}
