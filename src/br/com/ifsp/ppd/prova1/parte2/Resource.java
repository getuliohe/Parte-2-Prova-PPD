package br.com.ifsp.ppd.prova1.parte2;

public class Resource {
    private int readersCount = 0;
    private boolean writerActive = false; // Flag para saber se o escritor está ativo

    private String state = "NOTHING";  // Estado do recurso

    // Metodo para os leitores acessarem o recurso
    public synchronized void read(Reader reader) throws InterruptedException {
        // Espera até que não haja escritor ativo
        while (writerActive) {
            System.out.println("Leitor " + reader.getName() + " aguardando para ler.");
            wait();  // Espera até que o escritor termine ou não haja escritor ativo
        }
        // Aumenta o contador de leitores ativos
        readersCount++;
        System.out.println("Leitor " + reader.getName() + " está lendo.");

        // Simula o tempo de leitura
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Decrementa o contador de leitores ativos
        finishReading(reader);
    }

    // Metodo para um leitor terminar de ler
    public synchronized void finishReading(Reader reader) {
        readersCount--;
        System.out.println("Leitor " + reader.getName() + " terminou de ler.");

        // Se não houver mais leitores, notifica todos os threads esperando
        if (readersCount == 0) {
            notifyAll();
        }
    }

    // Metodo para os escritores acessarem o recurso
    public synchronized void write(Writer writer) throws InterruptedException {
        // Espera enquanto houver leitores ou outro escritor
        while (readersCount > 0 || writerActive) {
            System.out.println("Escritor " + writer.getName() + " aguardando para escrever.");
            wait();  // Espera até que não haja leitores ou outro escritor
        }

        // Agora o escritor pode escrever
        writerActive = true;
        System.out.println("Escritor " + writer.getName() + " está escrevendo.");

        // Simula o tempo de escrita
        writer.writing();

        // Termina a escrita
        writerActive = false;
        System.out.println("Escritor " + writer.getName() + " terminou de escrever.");

        // Notifica todos os threads esperando para ler ou escrever
        notifyAll();
    }
}
