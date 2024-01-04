package example.synchronization;

public class Monitor {
    public static void main(String[] args) {
        final Resource resource = new Resource(0);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                resource.plus();
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                resource.minus();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
            System.out.println(resource.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Resource {
        int value;

        public Resource(int value) {
            this.value = value;
        }

        public synchronized void plus() {
            value++;
        }

        public synchronized void minus() {
            value--;
        }

        public int getValue() {
            return value;
        }

    }
}
