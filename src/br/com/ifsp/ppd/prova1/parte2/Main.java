package br.com.ifsp.ppd.prova1.parte2;

public class Main {

    public static void main(String[] args) {
        final int NUMBER_OF_WRITERS = 2;
        final int NUMBER_OF_READERS = 3;

        Resource resource = new Resource();


        for (int i = 0; i < NUMBER_OF_READERS; i++) {
            Reader reader = new Reader(resource, i + 1);
            Thread readerThread = new Thread(reader);
            readerThread.start();
        }


        for (int i = 0; i < NUMBER_OF_WRITERS; i++) {
            Writer writer = new Writer(resource, i + 1);
            Thread writerThread = new Thread(writer);
            writerThread.start();
        }
    }
}
