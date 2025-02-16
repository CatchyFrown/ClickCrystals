package io.github.itzispyder.clickcrystals.util;

import io.github.itzispyder.clickcrystals.util.misc.Pair;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class MathUtils {

    public static double avg(Integer... ints) {
        final List<Integer> list = Arrays.stream(ints).filter(Objects::nonNull).toList();
        return avg(list);
    }

    public static double avg(List<Integer> ints) {
        double sum = 0.0;
        for (Integer i : ints) sum += i;
        return sum / ints.size();
    }

    public static double round(double value, int nthPlace) {
        return Math.floor(value * nthPlace) / nthPlace;
    }

    public static int minMax(int val, int min, int max) {
        return Math.min(max, Math.max(min, val));
    }

    public static double minMax(double val, double min, double max) {
        return Math.min(max, Math.max(min, val));
    }

    public static Pair<Float, Float> toPolar(double x, double y, double z) {
        double pi2 = 2 * Math.PI;
        float pitch, yaw;

        if (x == 0 && z == 0) {
            pitch = y > 0 ? -90 : 90;
            return Pair.of(pitch, 0.0F);
        }

        double theta = Math.atan2(-x, z);
        yaw = (float)Math.toDegrees((theta + pi2) % pi2);

        double xz = Math.sqrt(x * x + z * z);
        pitch = (float)Math.toDegrees(Math.atan(-y / xz));

        return Pair.of(pitch, yaw);
    }

    /* // old code, not efficient!!!
    public static Pair<Float, Float> toPolar(double x, double y, double z) {
        double pitch = wrapDegrees(-Math.toDegrees(Math.asin(y)));
        double h = -Math.cos(-Math.toRadians(pitch));
        double yaw = wrapDegrees(-Math.toDegrees(Math.asin(x / h) + Math.PI));
        return Pair.of((float)pitch, (float)yaw);
    }
     */

    public static float cosInverse(double a) {
        return (float)Math.toDegrees(Math.acos(a));
    }

    public static float sinInverse(double a) {
        return (float)Math.toDegrees(Math.asin(a));
    }

    public static float tanInverse(double a) {
        return (float)Math.toDegrees(Math.atan(a));
    }

    public static boolean isWrapped(double deg) {
        double f = deg % 360.0;
        return f < 180 && f >= -180;
    }

    public static double wrapDegrees(double deg) {
        double f = deg % 360.0;

        if (f >= 180.0) {
            f -= 360.0;
        }
        if (f < -180.0) {
            f += 360.0;
        }

        return f;
    }

    public static double angleBetween(double deg1, double deg2) {
        double diff = deg2 - deg1;
        deg1 = wrapDegrees(deg1);
        deg2 = wrapDegrees(deg2);

        if ((deg1 < -90 && deg2 > 90) || (deg1 > 90 && deg2 < -90)) {
            return (180 - Math.abs(deg1)) + (180 - Math.abs(deg2));
        }
        if (diff >= 180 || diff < -180) {
            return Math.abs(deg2 - deg1);
        }
        return Math.abs(wrapDegrees(diff));
    }

    public static int exp(int val, int power) {
        int base = val;
        for (int i = 1; i < power; i++) {
            val *= base;
        }
        return val;
    }

    public static double exp(double val, double power) {
        double base = val;
        for (int i = 1; i < power; i++) {
            val *= base;
        }
        return val;
    }

    public static String getSystemLogTime() {
        LocalDateTime time = LocalDateTime.now();
        return twoDigitFormat(time.getHour()) + ":" + twoDigitFormat(time.getMinute()) + ":" + twoDigitFormat(time.getSecond());
    }

    public static String twoDigitFormat(int i) {
        return i < 10 && i >= 0 ? "0" + i : "" + i;
    }

    public static String twoDigitFormat(long i) {
        return i < 10L && i >= 0L ? "0" + i : "" + i;
    }
}
