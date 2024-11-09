package br.com.ifsp.ppd.prova1.parte2;

public class Writer implements Runnable {
    private int numberOfWrite = 0;
    private String name;
    private Resource resource;
    private final int MAX_OF_WRITING = 100;

    public Writer(Resource resource, int i) {
        this.resource = resource;
        this.name = "Escritor " + i;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (this.numberOfWrite < MAX_OF_WRITING) {
            try {
                resource.write(this);  // Tenta escrever
                this.numberOfWrite++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " terminou de escrever.");
    }

    public void writing() throws InterruptedException {
        Thread.sleep(1);  // Simula o tempo de escrita
    }
}
