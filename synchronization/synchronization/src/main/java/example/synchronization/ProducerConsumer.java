package example.synchronization;

public class ProducerConsumer {
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
            // 0을 기대하지만, 0 이 안나옴
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

        public void plus() {
            value++;
        }

        public void minus() {
            value--;
        }

        public int getValue() {
            return value;
        }

    }
}
