package br.com.ifsp.ppd.prova1.parte2;

public class Reader implements Runnable {
    private int numberOfRead = 0;
    private String name;
    private Resource resource;
    private final int MAX_OF_READING = 100;

    public Reader(Resource resource, int i) {
        this.resource = resource;
        this.name = "Leitor " + i;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (this.numberOfRead < MAX_OF_READING) {
            try {
                resource.read(this);  // Tenta ler
                this.numberOfRead++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " terminou de ler.");
    }
}
