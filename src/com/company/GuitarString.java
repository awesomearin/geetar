package com.company;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GuitarString
{
    private Queue<Double> ringbuffer = new LinkedList<>();
    private int length;
    private int time;
    private static final int SAMPLINGRATE = 44100;
    private static final double ENERGYDECAY = .996;

    public GuitarString(double frequency) {
        time = 0;
        int l = (int) Math.ceil(SAMPLINGRATE/frequency);
        length = l;
    }

    public GuitarString(double[] init) {
        time = 0;
        ringbuffer = new LinkedList<Double>();
        for (int i = 0; i < init.length; i ++) {
            ringbuffer.add(init[i]);
        }
    }

    public void plink() {
        for (int i = 0; i < length; i ++) {
            double rng = Math.random() - 0.5;
            ringbuffer.add(rng);
        }
    }

    public void tick() {
        double first;
        if (!ringbuffer.isEmpty()) {
            first = ringbuffer.remove();
        } else {
            first = 0;
        }
        double second = sample();
        ringbuffer.add(ENERGYDECAY * (first + second)/2);
        time++;
    }

    public double sample() {
        if (!ringbuffer.isEmpty()) {
            return ringbuffer.peek();
        } else {
            return 0;
        }
    }

    public int time() {
        return time;
    }


}
