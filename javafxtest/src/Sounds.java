package Ingame;

import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/19/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sounds {

    public static void playWAV(String fileName, int loopCount) {
        AudioPlayer BGP = AudioPlayer.player;
        ContinuousAudioDataStream loop = null;
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            //repeat times
            clip.loop(loopCount);
            clip.start();

        } catch (Exception e) {
            //e.printStackTrace();
        }
        BGP.start(loop);

    }

    //Play.mid files
    public static void playMIDI(String fileName, int loopCount) {
        AudioPlayer BGP = AudioPlayer.player;
        ContinuousAudioDataStream loop = null;
        try {
            File inputFile = new File(fileName);
            Sequence seq;
            Sequencer sequencer;
            sequencer = MidiSystem.getSequencer();
            seq = MidiSystem.getSequence(inputFile);
            sequencer.setSequence(seq);
            //repeat times
            sequencer.setLoopCount(loopCount);
            sequencer.open();
            sequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        BGP.start(loop);
    }

    public static void main (String args[]){
        playMIDI("Images/RPGTheme.mid",1000);
    }
}
