package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;

import com.am1goo.playdate4j.sdk.SoundBridge.Note;
import com.am1goo.playdate4j.sdk.SoundBridge.StereoVolume;

public class Sound {

	private static final SoundBridge bridge = new SoundBridge();
	
	private static final List<SoundChannel> channels = new ArrayList<SoundChannel>();
	private static final List<PDSynthSignalValue> modulators = new ArrayList<PDSynthSignalValue>();
	private static final List<AudioSample> audioSamples = new ArrayList<AudioSample>();
	private static final List<FilePlayer> filePlayers = new ArrayList<FilePlayer>();
	private static final List<SamplePlayer> samplePlayers = new ArrayList<SamplePlayer>();
	private static final List<PDSynth> synths = new ArrayList<PDSynth>();
	private static final List<PDSynthSignal> signals = new ArrayList<PDSynthSignal>();
	private static final List<PDSynthInstrument> instruments = new ArrayList<PDSynthInstrument>();
	private static final List<PDSynthLFO> lfos = new ArrayList<PDSynthLFO>();
	private static final List<PDSynthEnvelope> envelopes = new ArrayList<PDSynthEnvelope>();
	private static final List<OnePoleFilter> onePoleFilters = new ArrayList<OnePoleFilter>();
	private static final List<TwoPoleFilter> twoPoleFilters = new ArrayList<TwoPoleFilter>();
	private static final List<BitCrusher> bitCrushers = new ArrayList<BitCrusher>();
	private static final List<RingModulator> ringModulators = new ArrayList<RingModulator>();
	private static final List<Overdrive> overdrives = new ArrayList<Overdrive>(); 
	private static final List<DelayLine> delayLines = new ArrayList<DelayLine>();
	private static final List<DelayLineTap> delayLineTaps = new ArrayList<DelayLineTap>();
	private static final List<SoundSequence> soundSequences = new ArrayList<SoundSequence>();
	private static final List<ControlSignal> controlSignals = new ArrayList<ControlSignal>();
	private static final List<SequenceTrack> sequenceTracks = new ArrayList<SequenceTrack>();
	
	private static SoundChannel defaultChannel = null;
	
	/* audio */
	public static String getError() {
		return bridge.getError();
	}
	
	public static int getCurrentTime() {
		return bridge.getCurrentTime();
	}
	
	public static SoundChannel getDefaultChannel() {
		if (defaultChannel != null)
			return defaultChannel;
		
		long ptr = bridge.channel().getDefaultChannel();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		defaultChannel = new SoundChannel(pointer);
		channels.add(defaultChannel);
		return defaultChannel;
	}
	
	public static boolean addChannel(SoundChannel channel) {
		if (channel == null)
			return false;

		return bridge.channel().addChannel(channel.getPointer().getValue());
	}
	
	public static boolean removeChannel(SoundChannel channel) {
		if (channel == null)
			return false;

		if (channel.ptr.invalid())
			return false;
		
		boolean removed = bridge.channel().removeChannel(channel.ptr.getValue());
		if (!removed)
			return false;
		
		channel.ptr.invalidate();
		channels.remove(channel);
		return true;
	}
	
	public static void setOutputsActive(int headphone, int speaker) {
		bridge.setOutputsActive(headphone, speaker);
	}
	
	/* channels */
	public static SoundChannel newChannel() {
		long ptr = bridge.channel().newChannel();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		SoundChannel channel = new SoundChannel(pointer);
		channels.add(channel);
		return channel;
	}

	public static SoundChannel freeChannel(SoundChannel channel) {
		if (channel == null)
			return null;

		if (channel.ptr.invalid())
			return null;
		
		bridge.channel().freeChannel(channel.getPointer().getValue());
		channel.ptr.invalidate();
		channels.remove(channel);
		return null;
	}
	
	private static PDSynthSignalValue findModulator(long ptr) {
		for (PDSynthSignalValue mod : modulators) {
			if (mod.ptr.getValue() == ptr) {
				return mod;
			}
		}
		return null;
	}
	
	public static AudioSample newSampleBuffer(int length) {
		long ptr = bridge.audioSample().newSampleBuffer(length);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		AudioSample sample = new AudioSample(pointer, null);
		audioSamples.add(sample);
		return sample;
	}
	
	public static AudioSample loadSample(String path) {
		long ptr = bridge.audioSample().load(path);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		AudioSample sample = new AudioSample(pointer, path);
		audioSamples.add(sample);
		return sample;
	}
	
	public static void loadIntoSample(AudioSample sample, String path) {
		if (sample == null)
			return;
		
		bridge.audioSample().loadIntoSample(sample.ptr.getValue(), path);
		sample.path = path;
	}
	
	public static AudioSample freeSample(AudioSample sample) {
		if (sample == null)
			return null;

		if (sample.ptr.invalid())
			return null;
		
		bridge.audioSample().freeSample(sample.ptr.getValue());
		sample.ptr.invalidate();
		audioSamples.remove(sample);
		return null;
	}
	
	public static FilePlayer newFilePlayer() {
		long ptr = bridge.filePlayer().newPlayer();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		FilePlayer player = new FilePlayer(pointer, null);
		filePlayers.add(player);
		return player;
	}
	
	public static void loadIntoPlayer(FilePlayer player, String path) {
		if (player == null)
			return;
		
		bridge.filePlayer().loadIntoPlayer(player.ptr.getValue(), path);
		player.path = path;
	}
	
	public static FilePlayer freePlayer(FilePlayer player) {
		if (player == null)
			return null;

		if (player.ptr.invalid())
			return null;
		
		bridge.filePlayer().freePlayer(player.ptr.getValue());
		player.ptr.invalidate();
		filePlayers.remove(player);
		return null;
	}
	
	public static SamplePlayer newSamplePlayer() {
		long ptr = bridge.samplePlayer().newPlayer();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		SamplePlayer player = new SamplePlayer(pointer);
		samplePlayers.add(player);
		return player;
	}
	
	public static SamplePlayer freePlayer(SamplePlayer player) {
		if (player == null)
			return null;
		
		if (player.ptr.invalid())
			return null;
		
		bridge.samplePlayer().freePlayer(player.ptr.getValue());
		player.ptr.invalidate();
		samplePlayers.remove(player);
		return null;
	}
	
	public static PDSynth newSynth() {
		long ptr = bridge.synth().newSynth();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		PDSynth synth = new PDSynth(pointer);
		synths.add(synth);
		return synth;
	}
	
	public static PDSynth freeSynth(PDSynth synth) {
		if (synth == null)
			return null;
		
		if (synth.ptr.invalid())
			return null;
		
		bridge.synth().freeSynth(synth.ptr.getValue());
		synth.ptr.invalidate();
		synths.remove(synth);
		return null;
	}
	
	private static PDSynth findSynth(long ptr) {
		for (PDSynth synth : synths) {
			if (synth.ptr.getValue() == ptr) {
				return synth;
			}
		}
		return null;
	}
	
	public static PDSynthSignal newSignal() {
		long ptr = bridge.signal().newSignal();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		PDSynthSignal signal = new PDSynthSignal(pointer);
		signals.add(signal);
		return signal;
	}
	
	public static PDSynthSignal freeSignal(PDSynthSignal signal) {
		if (signal == null)
			return null;
		
		if (signal.ptr.invalid())
			return null;
		
		bridge.signal().freeSignal(signal.ptr.getValue());
		signal.ptr.invalidate();
		signals.remove(signal);
		return null;
	}
	
	public static PDSynthInstrument newInstrument() {
		long ptr = bridge.instrument().newInstrument();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		PDSynthInstrument instrument = new PDSynthInstrument(pointer);
		instruments.add(instrument);
		return instrument;
	}
	
	public static PDSynthInstrument freeInstrument(PDSynthInstrument instrument) {
		if (instrument == null)
			return null;
		
		if (instrument.ptr.invalid())
			return null;
		
		bridge.instrument().freeInstrument(instrument.ptr.getValue());
		instrument.ptr.invalidate();
		instruments.remove(instrument);
		return null;
	}
	
	private static PDSynthInstrument findInstrument(long ptr) {
		for (PDSynthInstrument instrument : instruments) {
			if (instrument.ptr.getValue() == ptr)
				return instrument;
		}
		return null;
	}
	
	public static PDSynthEnvelope newEnvelope(float attack, float decay, float sustain, float release) {
		long ptr = bridge.envelope().newEnvelope(attack, decay, sustain, release);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		PDSynthEnvelope envelope = new PDSynthEnvelope(pointer);
		envelopes.add(envelope);
		return envelope;
	}
	
	public static PDSynthEnvelope freeEnvelope(PDSynthEnvelope envelope) {
		if (envelope == null)
			return null;
		
		if (envelope.ptr.invalid())
			return null;
		
		bridge.envelope().freeEnvelope(envelope.ptr.getValue());
		envelope.ptr.invalidate();
		envelopes.remove(envelope);
		return null;
	}
	
	private static PDSynthEnvelope findEnvelope(long ptr) {
		for (PDSynthEnvelope envelope : envelopes) {
			if (envelope.ptr.getValue() == ptr) {
				return envelope;
			}
		}
		return null;
	}
	
	public static PDSynthLFO newLFO(LFOType type) {
		long ptr = bridge.lfo().newLFO(type.getValue());
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		PDSynthLFO lfo = new PDSynthLFO(pointer);
		lfos.add(lfo);
		return lfo;
	}
	
	public static PDSynthLFO freeLFO(PDSynthLFO lfo) {
		if (lfo == null)
			return null;
		
		if (lfo.ptr.invalid())
			return null;
		
		bridge.lfo().freeLFO(lfo.ptr.getValue());
		lfo.ptr.invalidate();
		lfos.remove(lfo);
		return null;
	}
	
	private static PDSynthLFO findLFO(long ptr) {
		for (PDSynthLFO lfo : lfos) {
			if (lfo.ptr.getValue() == ptr) {
				return lfo;
			}
		}
		return null;
	}
	
	public static OnePoleFilter newOnePoleFilter() {
		long ptr = bridge.onePoleFilter().newFilter();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		OnePoleFilter filter = new OnePoleFilter(pointer);
		onePoleFilters.add(filter);
		return filter;
	}
	
	public static OnePoleFilter freeFilter(OnePoleFilter filter) {
		if (filter == null)
			return null;
		
		if (filter.ptr.invalid())
			return null;
		
		bridge.onePoleFilter().freeFilter(filter.ptr.getValue());
		filter.ptr.invalidate();
		onePoleFilters.remove(filter);
		return null;
	}
	
	public static TwoPoleFilter newTwoPoleFilter() {
		long ptr = bridge.twoPoleFilter().newFilter();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		TwoPoleFilter filter = new TwoPoleFilter(pointer);
		twoPoleFilters.add(filter);
		return filter;
	}
	
	public static TwoPoleFilter freeFilter(TwoPoleFilter filter) {
		if (filter == null)
			return null;
		
		if (filter.ptr.invalid())
			return null;
		
		bridge.twoPoleFilter().freeFilter(filter.ptr.getValue());
		filter.ptr.invalidate();
		twoPoleFilters.remove(filter);
		return null;
	}
	
	public static BitCrusher newBitCrusher() {
		long ptr = bridge.bitCrusher().newBitCrusher();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		BitCrusher filter = new BitCrusher(pointer);
		bitCrushers.add(filter);
		return filter;
	}
	
	public static BitCrusher freeBitCrusher(BitCrusher filter) {
		if (filter == null)
			return null;
		
		if (filter.ptr.invalid())
			return null;
		
		bridge.bitCrusher().freeBitCrusher(filter.ptr.getValue());
		filter.ptr.invalidate();
		bitCrushers.remove(filter);
		return null;
	}
	
	public static RingModulator newRingModulator() {
		long ptr = bridge.ringModulator().newRingMod();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		RingModulator filter = new RingModulator(pointer);
		ringModulators.add(filter);
		return filter;
	}
	
	public static RingModulator freeRingModulator(RingModulator filter) {
		if (filter == null)
			return null;
		
		if (filter.ptr.invalid())
			return null;
		
		bridge.ringModulator().freeRingMod(filter.ptr.getValue());
		filter.ptr.invalidate();
		ringModulators.remove(filter);
		return null;
	}
	
	public static Overdrive newOverdrive() {
		long ptr = bridge.overdrive().newOverdrive();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		Overdrive overdrive = new Overdrive(pointer);
		overdrives.add(overdrive);
		return overdrive;
	}
	
	public static Overdrive freeOverdrive(Overdrive overdrive) {
		if (overdrive == null)
			return null;
		
		if (overdrive.ptr.invalid())
			return null;
		
		bridge.overdrive().freeOverdrive(overdrive.ptr.getValue());
		overdrive.ptr.invalidate();
		overdrives.remove(overdrive);
		return null;
	}
	
	public static DelayLine newDelayLine(int length, boolean stereo) {
		long ptr = bridge.delayLine().newDelayLine(length, stereo);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		DelayLine delayLine = new DelayLine(pointer);
		delayLines.add(delayLine);
		return delayLine;
	}
	
	public static DelayLine freeDelayLine(DelayLine delayLine) {
		if (delayLine == null)
			return null;
		
		if (delayLine.ptr.invalid())
			return null;
		
		bridge.delayLine().freeDelayLine(delayLine.ptr.getValue());
		delayLine.ptr.invalidate();
		delayLines.remove(delayLine);
		return null;
	}
	
	public static DelayLineTap freeTap(DelayLineTap tap) {
		if (tap == null)
			return null;
		
		if (tap.ptr.invalid())
			return null;
		
		bridge.delayLineTap().freeTap(tap.ptr.getValue());
		tap.ptr.invalidate();
		delayLineTaps.remove(tap);
		return null;
	}
	
	private static DelayLineTap findDelayLineTap(long ptr) {
		for (DelayLineTap tap : delayLineTaps) {
			if (tap.ptr.getValue() == ptr)
				return tap;
		}
		return null;
	}
	
	public static SoundSequence newSequence() {
		long ptr = bridge.soundSequence().newSequence();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		SoundSequence seq = new SoundSequence(pointer);
		soundSequences.add(seq);
		return seq;
	}
	
	public static SoundSequence freeSequence(SoundSequence seq) {
		if (seq == null)
			return null;
		
		if (seq.ptr.invalid())
			return null;
		
		bridge.soundSequence().freeSequence(seq.ptr.getValue());
		seq.ptr.invalidate();
		soundSequences.remove(seq);
		return null;
	}
	
	private static SequenceTrack findSequenceTrack(long ptr) {
		for (SequenceTrack seq : sequenceTracks) {
			if (seq.ptr.getValue() == ptr)
				return seq;
		}
		return null;
	}
	
	public static ControlSignal newControlSignal() {
		long ptr = bridge.controlSignal().newSignal();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		ControlSignal signal = new ControlSignal(pointer);
		controlSignals.add(signal);
		return signal;
	}
	
	public static ControlSignal freeControlSignal(ControlSignal signal) {
		if (signal == null)
			return null;
		
		if (signal.ptr.invalid())
			return null;
		
		bridge.controlSignal().freeSignal(signal.ptr.getValue());
		signal.ptr.invalidate();
		controlSignals.remove(signal);
		return null;
	}
	
	private static ControlSignal findControlSignal(long ptr) {
		for (ControlSignal signal : controlSignals) {
			if (signal.ptr.getValue() == ptr)
				return signal;
		}
		return null;
	}
	
	public static SequenceTrack newTrack() {
		long ptr = bridge.controlSignal().newSignal();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		SequenceTrack track = new SequenceTrack(pointer);
		sequenceTracks.add(track);
		return track;
	}
	
	public static SequenceTrack freeTrack(SequenceTrack track) {
		if (track == null)
			return null;
		
		if (track.ptr.invalid())
			return null;
		
		bridge.sequenceTrack().freeTrack(track.ptr.getValue());
		track.ptr.invalidate();
		sequenceTracks.remove(track);
		return null;
	}
	
	public static class SoundChannel {
		
		private final Api.Pointer ptr;
		
		public SoundChannel(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeChannel(this);
		}
		
		public void addSource(SoundSource source) {
			if (source == null)
				return;

			bridge.channel().addSource(ptr.getValue(), source.getPointer().getValue());
		}
		
		public boolean removeSource(SoundSource source) {
			if (source == null)
				return false;

			return bridge.channel().removeSource(ptr.getValue(), source.getPointer().getValue());
		}
		
		public void addEffect(SoundEffect effect) {
			if (effect == null)
				return;

			bridge.channel().addEffect(ptr.getValue(), effect.getPointer().getValue());
		}
		
		public void removeEffect(SoundEffect effect) {
			if (effect == null)
				return;

			bridge.channel().removeEffect(ptr.getValue(), effect.getPointer().getValue());
		}
		
		public void setVolume(float volume) {
			bridge.channel().setVolume(ptr.getValue(), volume);
		}
		
		public float getVolume() {
			return bridge.channel().getVolume(ptr.getValue());
		}
		
		public void setVolumeModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;

			bridge.channel().setVolumeModulator(ptr.getValue(), mod.getPointer().getValue());
		}
		
		public PDSynthSignalValue getVolumeModulator() {
			long modPtr = bridge.channel().getVolumeModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setPan(float pan) {
			bridge.channel().setPan(ptr.getValue(), pan);
		}
		
		public void setPanModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;

			bridge.channel().setPanModulator(ptr.getValue(), mod.getPointer().getValue());
		}
		
		public PDSynthSignalValue getPanModulator() {
			long modPtr = bridge.channel().getPanModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public PDSynthSignalValue getDryLevelSignal() {
			long modPtr = bridge.channel().getDryLevelSignal(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public PDSynthSignalValue getWetLevelSignal() {
			long modPtr = bridge.channel().getWetLevelSignal(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class SoundSource {
		
		private final Api.Pointer ptr;
		
		private final SoundBridge.StereoVolume volume = new SoundBridge.StereoVolume();
		
		public SoundSource(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.source().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.source().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
		
		public boolean isPlaying() {
			return bridge.source().isPlaying(ptr.getValue());
		}
	}
	
	public static class SoundEffect {
		
		private final Api.Pointer ptr;
		
		public SoundEffect(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
	}
	
	public static class PDSynthSignalValue {
		
		private final Api.Pointer ptr;
		
		public PDSynthSignalValue(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
	}
	
	public static class AudioSample {
		
		private final Api.Pointer ptr;
		private String path;
		
		public AudioSample(Api.Pointer ptr, String path) {
			this.ptr = ptr;
			this.path = path;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public String getPath() {
			return path;
		}
		
		public float getLength() {
			return bridge.audioSample().getLength(ptr.getValue());
		}
		
		public void free() {
			Sound.freeSample(this);
		}
	}
	
	public static class FilePlayer {
		
		private final Api.Pointer ptr;
		private String path;
		
		private static final StereoVolume volume = new StereoVolume();
		
		public FilePlayer(Api.Pointer ptr, String path) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public String getPath() {
			return path;
		}
		
		public void free() {
			Sound.freePlayer(this);
		}
		
		public void pause() {
			bridge.filePlayer().pause(ptr.getValue());
		}
		
		public boolean play(int repeat) {
			return bridge.filePlayer().play(ptr.getValue(), repeat);
		}
		
		public boolean isPlaying() {
			return bridge.filePlayer().isPlaying(ptr.getValue());
		}
		
		public void setBufferLength(float bufferLen) {
			bridge.filePlayer().setBufferLength(ptr.getValue(), bufferLen);
		}
		
		public float getLength() {
			return bridge.filePlayer().getLength(ptr.getValue());
		}
		
		public boolean didUnderrun() {
			return bridge.filePlayer().didUnderrun(ptr.getValue());
		}

		public void setLoopRange(float start, float end) {
			bridge.filePlayer().setLoopRange(ptr.getValue(), start, end);
		}
		
		public void setOffset(float offset) {
			bridge.filePlayer().setOffset(ptr.getValue(), offset);
		}
		
		public float getOffset() {
			return bridge.filePlayer().getOffset(ptr.getValue());
		}
		
		public void setRate(float rate) {
			bridge.filePlayer().setRate(ptr.getValue(), rate);
		}
		
		public float getRate() {
			return bridge.filePlayer().getRate(ptr.getValue());
		}
		
		public void setStopOnUnderrun(boolean flag) {
			bridge.filePlayer().setStopOnUnderrun(ptr.getValue(), flag);
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.filePlayer().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.filePlayer().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
		
		public void stop() {
			bridge.filePlayer().stop(ptr.getValue());
		}
		
		public void fadeVolume(float lvol, float rvol, int len) {
			bridge.filePlayer().fadeVolume(ptr.getValue(), lvol, rvol, len);
		}
	}
	
	public static class SamplePlayer {
		
		private final Api.Pointer ptr;
		
		private static final StereoVolume volume = new StereoVolume();
	
		public SamplePlayer(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freePlayer(this);
		}
		
		public float getLength() {
			return bridge.samplePlayer().getLength(ptr.getValue());			
		}
		
		public boolean isPlaying() {
			return bridge.samplePlayer().isPlaying(ptr.getValue());
		}
		
		public boolean play(int repeat, float rate) {
			return bridge.samplePlayer().play(ptr.getValue(), repeat, rate);
		}
		
		public void stop() {
			bridge.samplePlayer().stop(ptr.getValue());			
		}
		
		public void setOffset(float offset) {
			bridge.samplePlayer().setOffset(ptr.getValue(), offset);
		}
		
		public float getOffset() {
			return bridge.samplePlayer().getOffset(ptr.getValue());
		}
		
		public void setRate(float rate) {
			bridge.samplePlayer().setRate(ptr.getValue(), rate);
		}
		
		public float getRate() {
			return bridge.samplePlayer().getRate(ptr.getValue());
		}
		
		public void setPlayRange(int start, int end) {
			bridge.samplePlayer().setPlayRange(ptr.getValue(), start, end);
		}
		
		public void setPaused(boolean paused) {
			bridge.samplePlayer().setPaused(ptr.getValue(), paused);
		}
		
		public void setSample(AudioSample audioSample) {
			if (audioSample == null)
				return;
			
			bridge.samplePlayer().setSample(ptr.getValue(), audioSample.ptr.getValue());
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.samplePlayer().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.samplePlayer().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
	}
	
	public static class PDSynth {
		
		private final Api.Pointer ptr;
		
		private static final StereoVolume volume = new StereoVolume();
		
		public PDSynth(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeSynth(this);
		}
		
		/* general */
		public void setWaveform(SoundWaveform wave) {
			bridge.synth().setWaveform(ptr.getValue(), wave.value);
		}
		
		public void setSample(AudioSample sample, long sustainStart, long sustainEnd) {
			if (sample == null)
				return;
			
			bridge.synth().setSample(ptr.getValue(), sample.ptr.getValue(), sustainStart, sustainEnd);
		}
		
		public boolean setWavetable(AudioSample sample, int log2size, int columns, int rows) {
			if (sample == null)
				return false;
			
			return bridge.synth().setWavetable(ptr.getValue(), sample.ptr.getValue(), log2size, columns, rows);
		}
		
		public void setAttackTime(float attack) {
			bridge.synth().setAttackTime(ptr.getValue(), attack);
		}
		
		public void setDecayTime(float decay) {
			bridge.synth().setDecayTime(ptr.getValue(), decay);
		}
		
		public void setSustainLevel(float sustain) {
			bridge.synth().setSustainLevel(ptr.getValue(), sustain);
		}
		
		public void setReleaseTime(float release) {
			bridge.synth().setReleaseTime(ptr.getValue(), release);
		}
		
		public PDSynthEnvelope getEnvelope() {
			long envelopePtr = bridge.synth().getEnvelope(ptr.getValue());
			PDSynthEnvelope found = findEnvelope(envelopePtr);
			if (found != null)
				return found;
			
			Api.Pointer envelopePointer = new Api.Pointer(envelopePtr);
			if (envelopePointer.invalid())
				return null;
			
			PDSynthEnvelope envelope = new PDSynthEnvelope(envelopePointer);
			envelopes.add(envelope);
			return envelope;
		}
		
		public void setTranspose(float halfSteps) {
			bridge.synth().setTranspose(ptr.getValue(), halfSteps);
		}
		
		public void setFrequencyModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.synth().setFrequencyModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getFrequencyModulator() {
			long modPtr = bridge.synth().getFrequencyModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setAmplitudeModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.synth().setAmplitudeModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getAmplitudeModulator() {
			long modPtr = bridge.synth().getAmplitudeModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void playNote(float freq, float vel, float len, long when) {
			bridge.synth().playNote(ptr.getValue(), freq, vel, len, when);
		}
		
		public void playMIDINote(MIDINote note, float vel, float len, long when) {
			bridge.synth().playMIDINote(ptr.getValue(), note.getValue(), vel, len, when);
		}
		
		public void noteOff(long when) {
			bridge.synth().noteOff(ptr.getValue(), when);
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.synth().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.synth().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
		
		public boolean isPlaying() {
			return bridge.synth().isPlaying(ptr.getValue());
		}
		
		/* synth parameters */
		public int getParameterCount() {
			return bridge.synth().getParameterCount(ptr.getValue());
		}
		
		public boolean setParameter(int num, float value) {
			return bridge.synth().setParameter(ptr.getValue(), num, value);
		}
		
		public boolean setParameterModulator(long synthPtr, int num, PDSynthSignalValue mod) {
			if (mod == null)
				return false;
			
			return bridge.synth().setParameter(ptr.getValue(), num, mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getParameterModulator(long synthPtr, int num) {
			long modPtr = bridge.synth().getParameterModulator(ptr.getValue(), num);
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
				
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class PDSynthSignal {
		
		private final Api.Pointer ptr;
		
		public PDSynthSignal(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeSignal(this);
		}
		
		public float getValue() {
			return bridge.signal().getValue(ptr.getValue());
		}
		
		public void setValueOffset(float offset) {
			bridge.signal().setValueOffset(ptr.getValue(), offset);
		}
		
		public void setValueScale(float scale) {
			bridge.signal().setValueScale(ptr.getValue(), scale);
		}
	}
	
	public static class PDSynthInstrument {
		
		private final Api.Pointer ptr;
		
		private static final StereoVolume volume = new StereoVolume();
		
		public PDSynthInstrument(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeInstrument(this);
		}
		
		public boolean addVoice(PDSynth synth, MIDINote rangeStart, MIDINote rangeEnd, float transpose) {
			if (synth == null)
				return false;
			
			if (synth.ptr.invalid())
				return false;
			
			return bridge.instrument().addVoice(ptr.getValue(), synth.ptr.getValue(), rangeStart.getValue(), rangeEnd.getValue(), transpose);
		}
		
		public PDSynth playNote(float frequency, float vel, float len, long when) {
			long synthPtr = bridge.instrument().playNote(ptr.getValue(), frequency, vel, len, when);
			PDSynth found = findSynth(synthPtr);
			if (found != null)
				return found;
			
			Api.Pointer synthPointer = new Api.Pointer(synthPtr);
			if (synthPointer.invalid())
				return null;
			
			PDSynth synth = new PDSynth(synthPointer);
			synths.add(synth);
			return synth;
		}
		
		public PDSynth playMIDINote(MIDINote note, float vel, float len, long when) {
			long synthPtr = bridge.instrument().playMIDINote(ptr.getValue(), note.getValue(), vel, len, when);
			PDSynth found = findSynth(synthPtr);
			if (found != null)
				return found;
			
			Api.Pointer synthPointer = new Api.Pointer(synthPtr);
			if (synthPointer.invalid())
				return null;
			
			PDSynth synth = new PDSynth(synthPointer);
			synths.add(synth);
			return synth;
		}
		
		public void noteOff(float note, long when) {
			bridge.instrument().noteOff(ptr.getValue(), note, when);
		}
		
		public void setPitchBend(float bend) {
			bridge.instrument().setPitchBend(ptr.getValue(), bend);
		}
		
		public void setPitchBendRange(float halfSteps) {
			bridge.instrument().setPitchBendRange(ptr.getValue(), halfSteps);
		}
		
		public void setTranspose(float halfSteps) {
			bridge.instrument().setTranspose(ptr.getValue(), halfSteps);
		}
		
		public void allNotesOff(long when) {
			bridge.instrument().allNotesOff(ptr.getValue(), when);
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.instrument().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.instrument().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
		
		public int activeVoiceCount() {
			return bridge.instrument().activeVoiceCount(ptr.getValue());
		}
	}
	
	public static class PDSynthLFO {
		
		private final Api.Pointer ptr;
		
		public PDSynthLFO(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeLFO(this);
		}
		
		public void setType(LFOType type) {
			bridge.lfo().setType(ptr.getValue(), type.getValue());
		}
		
		public void setRate(float rate) {
			bridge.lfo().setRate(ptr.getValue(), rate);
		}
		
		public void setPhase(float phase) {
			bridge.lfo().setPhase(ptr.getValue(), phase);
		}
		
		public void setStartPhase(float phase) {
			bridge.lfo().setStartPhase(ptr.getValue(), phase);
		}
		
		public void setCenter(float center) {
			bridge.lfo().setCenter(ptr.getValue(), center);
		}
		
		public void setDepth(float depth) {
			bridge.lfo().setDepth(ptr.getValue(), depth);
		}
		
		public void setArpeggiation(float[] steps) {
			bridge.lfo().setArpeggiation(ptr.getValue(), steps.length, steps);
		}
		
		public void setDelay(float holdoff, float ramptime) {
			bridge.lfo().setDelay(ptr.getValue(), holdoff, ramptime);
		}
		
		public void setRetrigger(boolean flag) {
			bridge.lfo().setRetrigger(ptr.getValue(), flag);
		}
		
		public float getValue() {
			return bridge.lfo().getValue(ptr.getValue());
		}
		
		public void setGlobal(boolean global) {
			bridge.lfo().setGlobal(ptr.getValue(), global);
		}
	}
	
	public static class PDSynthEnvelope {
		
		private final Api.Pointer ptr;
		
		public PDSynthEnvelope(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeEnvelope(this);
		}
		
		public void setAttack(float attack) {
			bridge.envelope().setAttack(ptr.getValue(), attack);
		}
		
		public void setDecay(float decay) {
			bridge.envelope().setDecay(ptr.getValue(), decay);
		}
		
		public void setSustain(float sustain) {
			bridge.envelope().setSustain(ptr.getValue(), sustain);
		}
		
		public void setRelease(float release) {
			bridge.envelope().setRelease(ptr.getValue(), release);
		}
		
		public void setCurvature(float amount) {
			bridge.envelope().setCurvature(ptr.getValue(), amount);
		}
		
		public void setVelocitySensitivity(float velsens) {
			bridge.envelope().setVelocitySensitivity(ptr.getValue(), velsens);
		}
		
		public void setRateScaling(float scaling, MIDINote start, MIDINote end) {
			bridge.envelope().setRateScaling(ptr.getValue(), scaling, start.value, end.value);
		}
		
		public void setLegato(boolean flag) {
			bridge.envelope().setLegato(ptr.getValue(), flag);
		}
		
		public void setRetrigger(boolean flag) {
			bridge.envelope().setRetrigger(ptr.getValue(), flag);
		}
		
		public float getValue() {
			return bridge.envelope().getValue(ptr.getValue());
		}
	}
	
	public static class OnePoleFilter {
		
		private final Api.Pointer ptr;
		
		public OnePoleFilter(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeFilter(this);
		}
		
		public void setParameter(float parameter) {
			bridge.onePoleFilter().setParameter(ptr.getValue(), parameter);
		}
		
		public void setParameterModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.onePoleFilter().setParameterModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getParameterModulator() {
			long modPtr = bridge.onePoleFilter().getParameterModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class TwoPoleFilter {
		
		private final Api.Pointer ptr;
		
		public TwoPoleFilter(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeFilter(this);
		}
		
		public void setType(TwoPoleFilterType type) {
			bridge.twoPoleFilter().setType(ptr.getValue(), type.getValue());
		}
		
		public void setFrequency(float frequency) {
			bridge.twoPoleFilter().setFrequency(ptr.getValue(), frequency);
		}
		
		public void setFrequencyModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.twoPoleFilter().setFrequencyModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getFrequencyModulator() {
			long modPtr = bridge.twoPoleFilter().getFrequencyModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setGain(float gain) {
			bridge.twoPoleFilter().setGain(ptr.getValue(), gain);
		}
		
		public void setResonance(float resonance) {
			bridge.twoPoleFilter().setResonance(ptr.getValue(), resonance);
		}
		
		public void setResonanceModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.twoPoleFilter().setResonanceModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getResonanceModulator() {
			long modPtr = bridge.twoPoleFilter().getResonanceModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class BitCrusher {
		
		private final Api.Pointer ptr;
		
		public BitCrusher(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeBitCrusher(this);
		}
		
		public void setAmount(float amount) {
			bridge.bitCrusher().setAmount(ptr.getValue(), amount);
		}
		
		public void setAmountModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.bitCrusher().setAmountModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getAmountModulator() {
			long modPtr = bridge.bitCrusher().getAmountModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setUndersampling(float undersample) {
			bridge.bitCrusher().setUndersampling(ptr.getValue(), undersample);
		}
		
		public void setUndersampleModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.bitCrusher().setUndersampleModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getUndersampleModulator() {
			long modPtr = bridge.bitCrusher().getUndersampleModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class RingModulator {
		
		private final Api.Pointer ptr;
		
		public RingModulator(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeRingModulator(this);
		}
		
		public void setFrequency(float frequency) {
			bridge.ringModulator().setFrequency(ptr.getValue(), frequency);
		}
		
		public void setFrequencyModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			bridge.ringModulator().setFrequencyModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getFrequencyModulator() {
			long modPtr = bridge.ringModulator().getFrequencyModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class Overdrive {
		
		private final Api.Pointer ptr;
		
		public Overdrive(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeOverdrive(this);
		}
		
		public void setGain(float gain) {
			bridge.overdrive().setGain(ptr.getValue(), gain);
		}
		
		public void setLimit(float limit) {
			bridge.overdrive().setLimit(ptr.getValue(), limit);
		}
		
		public void setLimitModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			if (mod.ptr.invalid())
				return;
			
			bridge.overdrive().setLimitModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getLimitModulator() {
			long modPtr = bridge.overdrive().getLimitModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setOffset(float offset) {
			bridge.overdrive().setOffset(ptr.getValue(), offset);
		}
		
		public void setOffsetModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			if (mod.ptr.invalid())
				return;
			
			bridge.overdrive().setOffsetModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getOffsetModulator() {
			long modPtr = bridge.overdrive().getOffsetModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class DelayLine {
		
		private final Api.Pointer ptr;
		
		public DelayLine(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeDelayLine(this);
		}
		
		public void setLength(int frames) {
			bridge.delayLine().setLength(ptr.getValue(), frames);
		}
		
		public void setFeedback(float fb) {
			bridge.delayLine().setFeedback(ptr.getValue(), fb);
		}
		
		public DelayLineTap addTap(int delay) {
			long tapPtr = bridge.delayLine().addTap(ptr.getValue(), delay);
			DelayLineTap found = findDelayLineTap(tapPtr);
			if (found != null)
				return found;
			
			Api.Pointer tapPointer = new Api.Pointer(tapPtr);
			if (tapPointer.invalid())
				return null;
			
			DelayLineTap tap = new DelayLineTap(tapPointer);
			delayLineTaps.add(tap);
			return tap;
		}
	}
	
	public static class DelayLineTap {
		
		private final Api.Pointer ptr;
		
		public DelayLineTap(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeTap(this);
		}
		
		public void setTapDelay(int frames) {
			bridge.delayLineTap().setTapDelay(ptr.getValue(), frames);
		}
		
		public void setTapDelayModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;
			
			if (mod.ptr.invalid())
				return;
			
			bridge.delayLineTap().setTapDelayModulator(ptr.getValue(), mod.ptr.getValue());
		}
		
		public PDSynthSignalValue getTapDelayModulator() {
			long modPtr = bridge.delayLineTap().getTapDelayModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setTapChannelsFlipped(boolean flip) {
			bridge.delayLineTap().setTapChannelsFlipped(ptr.getValue(), flip);
		}
	}

	public static class SoundSequence {
		
		private final Api.Pointer ptr;
		
		public SoundSequence(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeSequence(this);
		}
		
		public boolean loadMIDIFile(String path) {
			return bridge.soundSequence().loadMIDIFile(ptr.getValue(), path);
		}
		
		public void play() {
			bridge.soundSequence().play(ptr.getValue());
		}
		
		public void stop() {
			bridge.soundSequence().stop(ptr.getValue());
		}
		
		public boolean isPlaying() {
			return bridge.soundSequence().isPlaying(ptr.getValue());
		}
		
		public long getTime() {
			return bridge.soundSequence().getTime(ptr.getValue());
		}
		
		public void setTime(long time) {
			bridge.soundSequence().setTempo(ptr.getValue(), time);
		}
		
		public void setLoops(int startStep, int endStep, int loops) {
			bridge.soundSequence().setLoops(ptr.getValue(), startStep, endStep, loops);
		}
		
		public int getTempo() {
			return bridge.soundSequence().getTempo(ptr.getValue());
		}
		
		public void setTempo(float stepsPerSecond) {
			bridge.soundSequence().setTempo(ptr.getValue(), stepsPerSecond);
		}
		
		public int getLength() {
			return bridge.soundSequence().getLength(ptr.getValue());
		}
		
		public int getTrackCount() {
			return bridge.soundSequence().getTrackCount(ptr.getValue());
		}
		
		public SequenceTrack addTrack() {
			long trackPtr = bridge.soundSequence().addTrack(ptr.getValue());
			Api.Pointer trackPointer = new Api.Pointer(trackPtr);
			if (trackPointer.invalid())
				return null;
			
			SequenceTrack track = new SequenceTrack(trackPointer);
			sequenceTracks.add(track);
			return track;
		}
		
		public SequenceTrack getTrackAtIndex(int index) {
			long trackPtr = bridge.soundSequence().getTrackAtIndex(ptr.getValue(), index);
			SequenceTrack found = findSequenceTrack(trackPtr);
			if (found != null)
				return found;
			
			Api.Pointer trackPointer = new Api.Pointer(trackPtr);
			if (trackPointer.invalid())
				return null;
			
			SequenceTrack track = new SequenceTrack(trackPointer);
			sequenceTracks.add(track);
			return track;
		}
		
		public void setTrackAtIndex(SequenceTrack track, int index) {
			if (track == null)
				return;
			
			if (track.ptr.invalid())
				return;
			
			bridge.soundSequence().setTrackAtIndex(ptr.getValue(), track.ptr.getValue(), index);
		}
		
		public void allNotesOff() {
			bridge.soundSequence().allNotesOff(ptr.getValue());
		}
		
		public int getCurrentStep() {
			return bridge.soundSequence().getCurrentStep(ptr.getValue());
		}
		
		public void setCurrentStep(int step, int timeOffset, int playNotes) {
			bridge.soundSequence().setCurrentStep(ptr.getValue(), step, timeOffset, playNotes);
		}
	}

	public static class ControlSignal {
		
		private final Api.Pointer ptr;
		
		public ControlSignal(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeControlSignal(this);
		}
		
		public void clearEvents() {
			bridge.controlSignal().clearEvents(ptr.getValue());
		}
		
		public void addEvent(int step, float value, int interpolate) {
			bridge.controlSignal().addEvent(ptr.getValue(), step, value, interpolate);
		}
		
		public void removeEvent(int step) {
			bridge.controlSignal().removeEvent(ptr.getValue(), step);
		}
		
		public int getMIDIControllerNumber() {
			return bridge.controlSignal().getMIDIControllerNumber(ptr.getValue());
		}
	}

	public static class SequenceTrack {
		
		private final Api.Pointer ptr;
		
		public SequenceTrack(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeTrack(this);
		}
		
		public void setInstrument(PDSynthInstrument instrument) {
			if (instrument == null)
				return;
			
			if (instrument.ptr.invalid())
				return;
			
			bridge.sequenceTrack().setInstrument(ptr.getValue(),instrument.ptr.getValue());
		}
		
		public PDSynthInstrument getInstrument() {
			long instrumentPtr = bridge.sequenceTrack().getInstrument(ptr.getValue());
			PDSynthInstrument found = findInstrument(instrumentPtr);
			if (found != null)
				return found;
			
			Api.Pointer instrumentPointer = new Api.Pointer(instrumentPtr);
			if (instrumentPointer.invalid())
				return null;
			
			PDSynthInstrument instrument = new PDSynthInstrument(instrumentPointer);
			instruments.add(instrument);
			return instrument;
		}
		
		public void addNoteEvent(long step, long length, MIDINote note, float vel) {
			bridge.sequenceTrack().addNoteEvent(ptr.getValue(), step, length, note.getValue(), vel);
		}
		
		public void removeNoteEvent(long step, MIDINote note) {
			bridge.sequenceTrack().removeNoteEvent(ptr.getValue(), step, note.getValue());
		}
		
		public void clearNotes() {
			bridge.sequenceTrack().clearNotes(ptr.getValue());
		}
		
		public int getLength() {
			return bridge.sequenceTrack().getLength(ptr.getValue());
		}
		
		public int getIndexForStep(long step) {
			return bridge.sequenceTrack().getIndexForStep(ptr.getValue(), step);
		}
		
		public boolean getNoteAtIndex(int index, Note note) {
			return bridge.sequenceTrack().getNoteAtIndex(ptr.getValue(), index, note);
		}
		
		public int getControlSignalCount() {
			return bridge.sequenceTrack().getControlSignalCount(ptr.getValue());
		}
		
		public ControlSignal getControlSignal(int idx) {
			long signalPtr = bridge.sequenceTrack().getControlSignal(ptr.getValue(), idx);
			ControlSignal found = findControlSignal(signalPtr);
			if (found != null)
				return found;

			Api.Pointer signalPointer = new Api.Pointer(signalPtr);
			if (signalPointer.invalid())
				return null;
			
			ControlSignal signal = new ControlSignal(signalPointer);
			controlSignals.add(signal);
			return signal;
		}
		
		public ControlSignal getSignalForController(int controller, boolean create) {
			long signalPtr = bridge.sequenceTrack().getSignalForController(ptr.getValue(), controller, create);
			ControlSignal found = findControlSignal(signalPtr);
			if (found != null)
				return found;
			
			Api.Pointer signalPointer = new Api.Pointer(signalPtr);
			if (signalPointer.invalid())
				return null;
			
			ControlSignal signal = new ControlSignal(signalPointer);
			controlSignals.add(signal);
			return signal;
		}
		
		public void clearControlEvents() {
			bridge.sequenceTrack().clearControlEvents(ptr.getValue());
		}
		
		public int activeVoiceCount() {
			return bridge.sequenceTrack().activeVoiceCount(ptr.getValue());
		}
		
		public int getPolyphony() {
			return bridge.sequenceTrack().getPolyphony(ptr.getValue());
		}
		
		public void setMuted(boolean mute) {
			bridge.sequenceTrack().setMuted(ptr.getValue(), mute);
		}
	}
	
	public enum SoundWaveform {
		Square(0),
		Triangle(1),
		Sine(2),
		Noise(3),
		Sawtooth(4),
		POPhase(5),
		PODigital(6),
		POVosim(7);
		
		final int value;
		
		SoundWaveform(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public static class MIDINote {
		
		private static final int[] logTable = new int[256];
		
		static {
		    logTable[0] = logTable[1] = 0;
		    for (int i = 2; i < 256; i++)
		    	logTable[i] = 1 + logTable[i / 2];
		    logTable[0] = -1;
		}
		
		private final float value;
		
		public MIDINote(float value) {
			this.value = value;
		}
		
		public float getValue() {
			return value;
		}
		
		public float getFrequency() {
			return frequencyOf(this);
		}
		
		public static float frequencyOf(MIDINote note) {
			return 440 * (float)Math.pow(2.0, (note.value - 69) / 12.0f);
		}
		
		public static MIDINote valueOf(float frequency) {
			
			float n = 12*log2f(frequency) - 36.376316562f;
			return new MIDINote(n);
		}
		
		private static final int log2f(float f) {
			int x = Float.floatToIntBits(f);
		    int c = x >> 23;

		    if (c != 0) {
		    	return c - 127;
		    }
		    else
		    {
		        int t = x >> 16;
		        if (t != 0)
		        	return logTable[t] - 133;
		        else
		        	return (x >> 8 != 0) ? logTable[t] - 141 : logTable[x] - 149;
		    }
		}
	}
	
	public enum LFOType {
		Square(0),
		Triangle(1),
		Sine(2),
		SampleAndHold(3),
		SawtoothUp(4),
		SawtoothDown(5),
		Arpeggiator(6),
		Function(7);
		
		final int value;
		
		LFOType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum TwoPoleFilterType {
		LowPass(0),
		HighPass(1),
		BandPass(2),
		Notch(3),
		PEQ(4),
		LowShelf(5),
		HighShelf(6);
		
		final int value;
		
		TwoPoleFilterType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
