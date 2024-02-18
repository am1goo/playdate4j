package com.am1goo.playdate4j.engine.utilities;

public class Easings {

    public static final Linear linear = new Linear();
    public static final Quadratic quadratic = new Quadratic();
    public static final Cubic cubic = new Cubic();
    public static final Quartic quartic = new Quartic();
    public static final Quintic quintic = new Quintic();
    public static final Sinusoidal sinusoidal = new Sinusoidal();
    public static final Exponential exponential = new Exponential();
    public static final Circular circular = new Circular();
    public static final Elastic elastic = new Elastic();
    public static final Bounce bounce = new Bounce();

    public static float formula(float k, Easing easing, Formula formula) {
        switch (formula) {
            case In: return easing.in(k);
            case Out: return easing.out(k);
            case InOut: return easing.inOut(k);
            default: return 0;
        }
    }

    public static class Linear implements Easing {

        public float in(float k) {
            return linear(k);
        }

        public float out(float k) {
            return linear(k);
        }

        public float inOut(float k) {
            return linear(k);
        }

        private static float linear(float k) {
            return k;
        }
    }

    public static class Quadratic implements Easing {

        public float in(float k) {
            return k * k;
        }

        public float out(float k) {
            return k * (2f - k);
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f) return 0.5f * k * k;
            return -0.5f * ((k -= 1f) * (k - 2f) - 1f);
        }
    }

    public static class Cubic implements Easing {

        public float in(float k) {
            return k * k * k;
        }

        public float out(float k) {
            return 1f + ((k -= 1f) * k * k);
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f) return 0.5f * k * k * k;
            return 0.5f * ((k -= 2f) * k * k + 2f);
        }
    }

    public static class Quartic implements Easing {

        public float in(float k) {
            return k * k * k * k;
        }

        public float out(float k) {
            return 1f - ((k -= 1f) * k * k * k);
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f)
                return 0.5f * k * k * k * k;
            return -0.5f * ((k -= 2f) * k * k * k - 2f);
        }
    }

    public static class Quintic implements Easing {

        public float in(float k) {
            return k * k * k * k * k;
        }

        public float out(float k) {
            return 1f + ((k -= 1f) * k * k * k * k);
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f)
                return 0.5f * k * k * k * k * k;
            return 0.5f * ((k -= 2f) * k * k * k * k + 2f);
        }
    }

    public static class Sinusoidal implements Easing {

        public float in(float k) {
            return (float)(1f - Math.cos(k * Math.PI / 2f));
        }

        public float out(float k) {
            return (float)(Math.sin(k * Math.PI / 2f));
        }

        public float inOut(float k) {
            return (float)(0.5f * (1f - Math.cos(Math.PI * k)));
        }
    }

    public static class Exponential implements Easing {

        public float in(float k) {
            return (float)(k == 0f ? 0f : Math.pow(1024f, k - 1f));
        }

        public float out(float k) {
            return (float)(k == 1f ? 1f : 1f - Math.pow(2f, -10f * k));
        }

        public float inOut(float k) {
            if (k == 0f)
                return 0f;
            if (k == 1f)
                return 1f;
            if ((k *= 2f) < 1f)
                return (float)(0.5f * Math.pow(1024f, k - 1f));
            return (float)(0.5f * (-Math.pow(2f, -10f * (k - 1f)) + 2f));
        }
    }

    public static class Circular implements Easing {

        public float in(float k) {
            return (float)(1f - Math.sqrt(1f - k * k));
        }

        public float out(float k) {
            return (float)(Math.sqrt(1f - ((k -= 1f) * k)));
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f)
                return (float)(-0.5f * (Math.sqrt(1f - k * k) - 1));
            return (float)(0.5f * (Math.sqrt(1f - (k -= 2f) * k) + 1f));
        }
    }

    public static class Elastic implements Easing {

        public float in(float k) {
            if (k == 0)
                return 0;
            if (k == 1)
                return 1;
            return (float)(-Math.pow(2f, 10f * (k -= 1f)) * Math.sin((k - 0.1f) * (2f * Math.PI) / 0.4f));
        }

        public float out(float k) {
            if (k == 0)
                return 0;
            if (k == 1)
                return 1;
            return (float)(Math.pow(2f, -10f * k) * Math.sin((k - 0.1f) * (2f * Math.PI) / 0.4f) + 1f);
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f)
                return  (float)(-0.5f * Math.pow(2f, 10f * (k -= 1f)) * Math.sin((k - 0.1f) * (2f * Math.PI) / 0.4f));
            return  (float)(Math.pow(2f, -10f * (k -= 1f)) * Math.sin((k - 0.1f) * (2f * Math.PI) / 0.4f) * 0.5f + 1f);
        }
    }

    public static class Back implements Easing {
        private final static float s = 1.70158f;
        private final static float s2 = 2.5949095f;

        public float in(float k) {
            return k * k * ((s + 1f) * k - s);
        }

        public float out(float k) {
            return (k -= 1f) * k * ((s + 1f) * k + s) + 1f;
        }

        public float inOut(float k) {
            if ((k *= 2f) < 1f)
                return 0.5f * (k * k * ((s2 + 1f) * k - s2));
            return 0.5f * ((k -= 2f) * k * ((s2 + 1f) * k + s2) + 2f);
        }
    }

    public static class Bounce implements Easing {

        public float in(float k) {
            return 1f - out(1f - k);
        }

        public float out(float k) {
            if (k < (1f / 2.75f)) {
                return 7.5625f * k * k;
            } else if (k < (2f / 2.75f)) {
                return 7.5625f * (k -= (1.5f / 2.75f)) * k + 0.75f;
            } else if (k < (2.5f / 2.75f)) {
                return 7.5625f * (k -= (2.25f / 2.75f)) * k + 0.9375f;
            } else {
                return 7.5625f * (k -= (2.625f / 2.75f)) * k + 0.984375f;
            }
        }

        public float inOut(float k) {
            if (k < 0.5f) return in(k * 2f) * 0.5f;
            return out(k * 2f - 1f) * 0.5f + 0.5f;
        }
    }

    public enum Formula {
        In,
        Out,
        InOut;
    }
}