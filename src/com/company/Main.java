package com.company;


public class Main {

    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args)
    {
        // crate an array to hold each guitar string
        GuitarString[] guitarString = new GuitarString[keyboard.length()];

        // compute the frequency of each key on the keyboard
        for(int i = 0; i < keyboard.length(); i++)
        {
            // this equation was taken from the assignment page to determine the note frequency
            double f = 440.0 * Math.pow(2.0, (i-24.0)/12.0);
            guitarString[i] = new GuitarString(f);
        }

        // run the simulation until the user terminates the program
        while (true) {

            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // wait for input
                char key = StdDraw.nextKeyTyped();

                // if the key pressed is on the keyboard, pluck the string
                if(keyboard.contains( String.valueOf(key) ) )
                    guitarString[keyboard.indexOf(key)].plink();

            }

            // compute the superposition of samples and advance the strings by one step
            double sample = 0;
            for(int i = 0; i < keyboard.length(); i++)
            {
                sample += guitarString[i].sample();
                guitarString[i].tick();
            }

            // play the sample on standard audio
            StdAudio.play(sample);



        }
    }
}
