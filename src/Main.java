

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
     * G F G F G F G F G F
     */
    public static void main(String ... args) {

        final int NUMBER_OF_PHILOSOPHERS_AND_FORKS = 5;

        final List<Fork> forks = new ArrayList<Fork>();

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS_AND_FORKS; i++) {
            String name = "FORK " + new Integer(i + 1).toString();
            forks.add(new Fork(name));
        }

        final List<Philosopher> philosophers = new ArrayList<Philosopher>();

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS_AND_FORKS; i++) {

            String name = "P" + new Integer(i + 1).toString();

            Fork leftFork = forks.get(i);
            Fork rightFork = forks.get((i + 1) % NUMBER_OF_PHILOSOPHERS_AND_FORKS);

            Philosopher philosopher = new Philosopher(name, leftFork, rightFork);

            philosophers.add(philosopher);

            new Thread(philosopher).start();
        }

        boolean hasRunningThreads;

        do {

            hasRunningThreads = false;

            for (Philosopher p: philosophers) {

                String messageLog = p.getName() + ": " + p.getState();
                messageLog += " | Thoughts = " + p.getNumberOfThoughts();
                messageLog += " | Meals: " + p.getNumberOfMeals();

                System.out.println(messageLog);

                hasRunningThreads |= p.getState() != "DONE";
            }

            System.out.println("********************************************\n\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

        } while(hasRunningThreads);
    }
}