package musica_midi;

import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Main {

	private static final String[] BASIC_NOTES = { "DÓ", "RÉ", "MI", "FÁ", "SOL", "LÁ", "SI"};
	private static final int OCTAVE_OFFSET = 7;

	public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		Synthesizer synthesizer = MidiSystem.getSynthesizer();
		synthesizer.open();
		MidiChannel[] channels = synthesizer.getChannels();
		MidiChannel channel = channels[0];
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int noteIndex = random.nextInt(BASIC_NOTES.length);
			String correctNote = BASIC_NOTES[noteIndex];
			int midiNote = noteIndex + (OCTAVE_OFFSET * 12);
			System.out.println("Escute a nota...");
			for (int i = 0; i < 100; i++) {
				channel.noteOn(midiNote, 80);
				Thread.sleep(100);
			}
			channel.noteOff(midiNote);
			System.out.print("Qual é a nota? (ex: DÓ, RÉ, MI, etc.): ");
			String userGuess = scanner.nextLine().toUpperCase();

			if(userGuess.equals("S")) {
				break;
			}else if (userGuess.equals(correctNote)) {
				System.out.println("Parabéns! Você acertou a nota.");
			} else {
				System.out.println("Que pena! A nota correta era: " + correctNote);
			}
		}
		synthesizer.close();
		scanner.close();
	}
}