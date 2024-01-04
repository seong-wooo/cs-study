package example.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLock {
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
        Lock lock = new ReentrantLock();

        public Resource(int value) {
            this.value = value;
        }

        public void plus() {
            lock.lock();
            try {
                value++;
            } finally {
                lock.unlock();
            }
        }

        public void minus() {
            lock.lock();
            try {
                value--;
            } finally {
                lock.unlock();
            }
        }

        public int getValue() {
            return value;
        }

    }
}
