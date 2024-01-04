package example.synchronization;

public class Semaphore {
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
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(1);

        public Resource(int value) {
            this.value = value;
        }

        public void plus() {
            try {
                semaphore.acquire();
                value++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        }

        public void minus() {
            try {
                semaphore.acquire();
                value--;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        }

        public int getValue() {
            return value;
        }

    }
}
