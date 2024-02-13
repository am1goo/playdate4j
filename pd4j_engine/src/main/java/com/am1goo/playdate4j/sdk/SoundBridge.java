package com.am1goo.playdate4j.sdk;

public class SoundBridge {

	private final ChannelBridge channel = new ChannelBridge();
	private final SourceBridge source = new SourceBridge();
	private final AudioSampleBridge audioSample = new AudioSampleBridge();
	private final FilePlayerBridge filePlayer = new FilePlayerBridge();
	private final SamplePlayerBridge samplePlayer = new SamplePlayerBridge();
	private final PDSynthBridge synth = new PDSynthBridge();
	private final PDSynthLFOBridge lfo = new PDSynthLFOBridge();
	private final PDSynthEnvelopeBridge envelope = new PDSynthEnvelopeBridge();
	private final OnePoleFilterBridge onePoleFilter = new OnePoleFilterBridge();
	private final TwoPoleFilterBridge twoPoleFilter = new TwoPoleFilterBridge();
	private final BitCrusherBridge bitCrusher = new BitCrusherBridge();
	private final RingModulatorBridge ringModulator = new RingModulatorBridge();
	
	public ChannelBridge channel() {
		return channel;
	}
	
	public SourceBridge source() {
		return source;
	}
	
	public AudioSampleBridge audioSample() {
		return audioSample;
	}
	
	public FilePlayerBridge filePlayer() {
		return filePlayer;
	}
	
	public SamplePlayerBridge samplePlayer() {
		return samplePlayer;
	}
	
	public PDSynthBridge synth() {
		return synth;
	}
	
	public PDSynthLFOBridge lfo() {
		return lfo;
	}
	
	public PDSynthEnvelopeBridge envelope() {
		return envelope;
	}
	
	public OnePoleFilterBridge onePoleFilter() {
		return onePoleFilter;
	}
	
	public TwoPoleFilterBridge twoPoleFilter() {
		return twoPoleFilter;
	}
	
	public BitCrusherBridge bitCrusher() {
		return bitCrusher;
	}
	
	public RingModulatorBridge ringModulator() {
		return ringModulator;
	}
	
    static {
        Sdk.loadRequiredLibraries();
    }

    /* audio */
	public native int getCurrentTime();
	public native void setOutputsActive(int headphone, int speaker);
	
	/* channels */
	public class ChannelBridge {

		public native long getDefaultChannel();
		public native boolean addChannel(long channelPtr);
		public native boolean removeChannel(long channelPtr);
		public native long newChannel();
		public native void freeChannel(long channelPtr);
		public native void addSource(long channelPtr, long sourcePtr);
		public native boolean removeSource(long channelPtr, long sourcePtr);
		public native void addEffect(long channelPtr, long effectPtr);
		public native void removeEffect(long channelPtr, long effectPtr);
		public native void setVolume(long channelPtr, float volume);
		public native float getVolume(long channelPtr);
		public native void setVolumeModulator(long channelPtr, long modPtr);
		public native long getVolumeModulator(long channelPtr);
		public native void setPan(long channelPtr, float pan);
		public native void setPanModulator(long channelPtr, long mod);
		public native long getPanModulator(long channelPtr);
		public native long getDryLevelSignal(long channelPtr);
		public native long getWetLevelSignal(long channelPtr);
	}
	
	/* sound source */
	public class SourceBridge {
		
		public native void setVolume(long sourcePtr, float lvol, float rvol);
		public native void getVolume(long sourcePtr, StereoVolume volume);
		public native boolean isPlaying(long sourcePtr);
	}
	
	/* audio sample */
	public class AudioSampleBridge {
		
		public native long newSampleBuffer(int length);
		public native long load(String path);
		public native void loadIntoSample(long samplePtr, String path);
		public native void freeSample(long samplePtr);
		public native float getLength(long samplePtr);
	}
	
	/* file player */
	public class FilePlayerBridge {
		
		public native long newPlayer();
		public native boolean loadIntoPlayer(long playerPtr, String path); 
		public native void freePlayer(long playerPtr);
		public native void pause(long playerPtr);
		public native boolean play(long playerPtr, int repeat);
		public native boolean isPlaying(long playerPtr);
		public native void setBufferLength(long playerPtr, float bufferLen);
		public native float getLength(long playerPtr);
		public native boolean didUnderrun(long playerPtr);
		public native void setLoopRange(long playerPtr, float start, float end);
		public native void setOffset(long playerPtr, float offset);
		public native float getOffset(long playerPtr);
		public native void setRate(long playerPtr, float rate);
		public native float getRate(long playerPtr);
		public native void setStopOnUnderrun(long playerPtr, boolean flag);
		public native void setVolume(long playerPtr, float lvol, float rvol);
		public native void getVolume(long playerPtr, StereoVolume volume);
		public native void stop(long playerPtr);
		public native void fadeVolume(long playerPtr, float lvol, float rvol, int len);
	}
	
	/* sample player */
	public class SamplePlayerBridge {
		
		public native long newPlayer();
		public native void freePlayer(long playerPtr);
		public native float getLength(long playerPtr);
		public native boolean isPlaying(long playerPtr);
		public native boolean play(long playerPtr, int repeat, float rate);
		public native void stop(long playerPtr);
		public native void setOffset(long playerPtr, float offset);
		public native float getOffset(long playerPtr);
		public native void setRate(long playerPtr, float rate);
		public native float getRate(long playerPtr);
		public native void setPlayRange(long playerPtr, int start, int end);
		public native void setPaused(long playerPtr, boolean paused);
		public native void setSample(long playerPtr, long samplePtr);
		public native void setVolume(long playerPtr, float lvol, float rvol);
		public native void getVolume(long playerPtr, StereoVolume volume);
	}
	
	/* PDSynth */
	public class PDSynthBridge {
		
		/* general */
		public native long newSynth();
		public native void freeSynth(long synthPtr);
		public native void setWaveform(long synthPtr, int wave);
		public native void setSample(long synthPtr, long samplePtr, long sustainStart, long sustainEnd);
		public native boolean setWavetable(long synthPtr, long samplePtr, int log2size, int columns, int rows);
		public native void setAttackTime(long synthPtr, float attack);
		public native void setDecayTime(long synthPtr, float decay);
		public native void setSustainLevel(long synthPtr, float sustain);
		public native void setReleaseTime(long synthPtr, float release);
		public native long getEnvelope(long synthPtr);
		public native void setTranspose(long synthPtr, float halfSteps);
		public native void setFrequencyModulator(long synthPtr, long modPtr);
		public native long getFrequencyModulator(long synthPtr);
		public native void setAmplitudeModulator(long synthPtr, long modPtr);
		public native long getAmplitudeModulator(long synthPtr);
		public native void playNote(long synthPtr, float freq, float vel, float len, long when);
		public native void playMIDINote(long synthPtr, float note, float vel, float len, long when);
		public native void noteOff(long synthPtr, long when);
		public native void setVolume(long synthPtr, float lvol, float rvol);
		public native void getVolume(long synthPtr, StereoVolume volume);
		public native boolean isPlaying(long synthPtr);
		
		/* synth parameters */
		public native int getParameterCount(long synthPtr);
		public native boolean setParameter(long synthPtr, int num, float value);
		public native boolean setParameterModulator(long synthPtr, int num, long modPtr);
		public native long getParameterModulator(long synthPtr, int num);
	}
	
	/* LFO */
	public class PDSynthLFOBridge {
		
		public native long newLFO(int type);
		public native void freeLFO(long lfoPtr);
		public native void setType(long lfoPtr, int type);
		public native void setRate(long lfoPtr, float rate);
		public native void setPhase(long lfoPtr, float phase);
		public native void setStartPhase(long lfoPtr, float phase);
		public native void setCenter(long lfoPtr, float center);
		public native void setDepth(long lfoPtr, float depth);
		public native void setArpeggiation(long lfoPtr, int nSteps, float[] steps);
		public native void setDelay(long lfoPtr, float holdoff, float ramptime);
		public native void setRetrigger(long lfoPtr, boolean flag);
		public native float getValue(long lfoPtr);
		public native void setGlobal(long lfoPtr, boolean global);
	}
	
	/* PDSynthEnvelope */
	public class PDSynthEnvelopeBridge {
		
		public native long newEnvelope(float attack, float decay, float sustain, float release);
		public native void freeEnvelope(long envelopePtr);
		public native void setAttack(long envelopePtr, float attack);
		public native void setDecay(long envelopePtr, float decay);
		public native void setSustain(long envelopePtr, float sustain);
		public native void setRelease(long envelopePtr, float release);
		public native void setCurvature(long envelopePtr, float amount);
		public native void setVelocitySensitivity(long envelopePtr, float velsens);
		public native void setRateScaling(long envelopePtr, float scaling, float start, float end);
		public native void setLegato(long envelopePtr, boolean flag);
		public native void setRetrigger(long envelopePtr, boolean flag);
		public native float getValue(long envelopePtr);
	}

	/* OnePoleFilter */
	public class OnePoleFilterBridge {
		
		public native long newFilter();
		public native void freeFilter(long filterPtr);
		public native void setParameter(long filterPtr, float parameter);
		public native void setParameterModulator(long filterPtr, long modPtr);
		public native long getParameterModulator(long filterPtr);
	}
	
	/* TwoPoleFilter */
	public class TwoPoleFilterBridge {
		
		public native long newFilter();
		public native void freeFilter(long filterPtr);
		public native void setType(long filterPtr, int type);
		public native void setFrequency(long filterPtr, float frequency);
		public native void setFrequencyModulator(long filterPtr, long modPtr);
		public native long getFrequencyModulator(long filterPtr);
		public native void setGain(long filterPtr, float gain);
		public native void setResonance(long filterPtr, float resonance);
		public native void setResonanceModulator(long filterPtr, long modPtr);
		public native long getResonanceModulator(long filterPtr);
	}
	
	/* BitCrusher */
	public class BitCrusherBridge {
		
		public native long newBitCrusher();
		public native void freeBitCrusher(long filterPtr);
		public native void setAmount(long filterPtr, float amount);
		public native void setAmountModulator(long filterPtr, long modPtr);
		public native long getAmountModulator(long filterPtr);
		public native void setUndersampling(long filterPtr, float undersample);
		public native void setUndersampleModulator(long filterPtr, long modPtr);
		public native long getUndersampleModulator(long filterPtr);
	}
	
	/* RingModulator */
	public class RingModulatorBridge {
		
		public native long newRingMod();
		public native void freeRingMod(long filterPtr);
		public native void setFrequency(long filterPtr, float frequency);
		public native void setFrequencyModulator(long filterPtr, long modPtr);
		public native long getFrequencyModulator(long filterPtr);
	}
	
	public static class StereoVolume {
		
		private float lvol;
		private float rvol;
		
		public float lvol() {
			return lvol;
		}
		
		public float rvol() {
			return rvol;
		}
		
		public void set(float lvol, float rvol) {
			this.lvol = lvol;
			this.rvol = rvol; 
		}
	}
}
