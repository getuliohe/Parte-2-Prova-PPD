

public class Fork {

    private String name;

    private Philosopher user;

    public Fork(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Philosopher getUser() {
        return this.user;
    }

    public synchronized void pickUp(Philosopher philosopher) throws InterruptedException {
        while (this.user != null && !this.user.equals(philosopher)) {
            wait();
        }

        this.user = philosopher;
    }

    public synchronized void pickDown(Philosopher philosopher) {
        if (this.user.equals(philosopher)) {
            this.user = null;
            notifyAll();
        }
    }

    public boolean isBeingUsed() {
        return this.user != null;
    }

    public boolean isBeingUsedBy(Philosopher philosopher) {

        try {
            if (this.user == null)
                return false;

            if (this.user.equals(philosopher))
                return true;

            return false;

        } catch (NullPointerException e) {
            return false;
        }
    }
}
