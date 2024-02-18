package com.am1goo.playdate4j.engine.primitives;

import com.am1goo.playdate4j.engine.utilities.Mathx;

public class Vector2f {

    private static final float EPSILON = 1E-05f;
    private static final float EPSILON_NORMAL_SQRT = 1E-15f;

    public float x;
    public float y;

    public static Vector2f zero() {
        return new Vector2f(0f, 0f);
    }

    public static Vector2f up() {
        return new Vector2f(0f, 1f);
    }

    public static Vector2f down() {
        return new Vector2f(0f, -1f);
    }

    public static Vector2f left() {
        return new Vector2f(-1f, 0f);
    }

    public static Vector2f right() {
        return new Vector2f(1f, 0f);
    }

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        set(x, y);
    }

    public void add(float dx, float dy) {
        set(x + dx, y + dy);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float sqrMagnitude() {
        return x * x + y * y;
    }

    public float magnitude() {
        return (float)Math.sqrt(sqrMagnitude());
    }

    public Vector2f normalized() {
        float m = magnitude();
        return new Vector2f(x / m, y / m);
    }

    public static float angle(Vector2f from, Vector2f to) {
        float num = (float)Math.sqrt(from.sqrMagnitude() * to.sqrMagnitude());
        if (num < EPSILON_NORMAL_SQRT)
            return 0f;

        float num2 = Mathx.clamp(dot(from, to) / num, -1f, 1f);
        float radians = (float)Math.acos(num2);
        return (float)Math.toDegrees(radians);
    }

    public static float distance(Vector2f a, Vector2f b) {
        float num = a.x - b.x;
        float num2 = a.y - b.y;
        return (float)Math.sqrt(num * num + num2 * num2);
    }

    public static float dot(Vector2f lhs, Vector2f rhs) {
        return lhs.x * rhs.x + lhs.y * rhs.y;
    }

    public static Vector2f lerp(Vector2f a, Vector2f b, float t) {
        t = Mathx.clamp01(t);
        return new Vector2f(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t);
    }

    public static Vector2f perpendicular(Vector2f inDirection) {
        return new Vector2f(0f - inDirection.y, inDirection.x);
    }

    public static Vector2f reflect(Vector2f inDirection, Vector2f inNormal) {
        float num = -2f * dot(inNormal, inDirection);
        return new Vector2f(num * inNormal.x + inDirection.x, num * inNormal.y + inDirection.y);
    }

    public static Vector2f rotateRadians(Vector2f vec, double radians) {
        float x = (float)(vec.x * Math.cos(radians) - vec.y * Math.sin(radians));
        float y = (float)(vec.x * Math.sin(radians) + vec.y * Math.cos(radians));
        return new Vector2f(x, y);
    }

    public static Vector2f rotateDegrees(Vector2f vec, double degrees) {
        double radians = Math.toRadians(degrees);
        return rotateRadians(vec, radians);
    }

    public static float signedAngle(Vector2f from, Vector2f to) {
        float angle = angle(from, to);
        float sign = Math.signum(from.x * to.y - from.y * to.x);
        return angle * sign;
    }
}
