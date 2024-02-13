package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;

import com.am1goo.playdate4j.sdk.SoundBridge.StereoVolume;

public class Sound {

	private static final SoundBridge bridge = new SoundBridge();
	
	private static final List<SoundChannel> channels = new ArrayList<SoundChannel>();
	private static final List<PDSynthSignalValue> modulators = new ArrayList<PDSynthSignalValue>();
	private static final List<AudioSample> audioSamples = new ArrayList<AudioSample>();
	private static final List<FilePlayer> filePlayers = new ArrayList<FilePlayer>();
	private static final List<SamplePlayer> samplePlayers = new ArrayList<SamplePlayer>();
	private static final List<PDSynth> pdsynths = new ArrayList<PDSynth>();
	private static final List<PDSynthLFO> lfos = new ArrayList<PDSynthLFO>();
	private static final List<PDSynthEnvelope> envelopes = new ArrayList<PDSynthEnvelope>();
	private static final List<OnePoleFilter> onePoleFilters = new ArrayList<OnePoleFilter>();
	private static final List<TwoPoleFilter> twoPoleFilters = new ArrayList<TwoPoleFilter>();
	private static final List<BitCrusher> bitCrushers = new ArrayList<BitCrusher>();
	private static final List<RingModulator> ringModulators = new ArrayList<RingModulator>();
	
	private static SoundChannel defaultChannel = null;
	
	/* audio */
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
		pdsynths.add(synth);
		return synth;
	}
	
	public static PDSynth freeSynth(PDSynth synth) {
		if (synth == null)
			return null;
		
		if (synth.ptr.invalid())
			return null;
		
		bridge.synth().freeSynth(synth.ptr.getValue());
		synth.ptr.invalidate();
		pdsynths.remove(synth);
		return null;
	}
	
	private static PDSynth findSynth(long ptr) {
		for (PDSynth synth : pdsynths) {
			if (synth.ptr.getValue() == ptr) {
				return synth;
			}
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
		
		public void getVolume(StereoVolume volume) {
			bridge.filePlayer().getVolume(ptr.getValue(), volume);
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
		
		public void getVolume(StereoVolume volume) {
			bridge.samplePlayer().getVolume(ptr.getValue(), volume);
		}
	}
	
	public static class PDSynth {
		
		private final Api.Pointer ptr;
		
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
		
		public void getVolume(StereoVolume volume) {
			bridge.synth().getVolume(ptr.getValue(), volume);
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
