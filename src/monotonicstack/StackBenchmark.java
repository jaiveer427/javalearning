package monotonicstack;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StackBenchmark {
    private static final int THREAD_COUNT = 16;       // High thread count to maximize collision
    private static final int OPERATIONS_PER_THREAD = 200_000; // Operations per thread
    private static final int CAPACITY = THREAD_COUNT * OPERATIONS_PER_THREAD;

    public static void main(String[] args) throws Exception {
        System.out.println("Warmup phase complete... Starting benchmark.\n");

        // Test 1: Synchronized Stack
        FixedSizeArrayStack syncStack = new FixedSizeArrayStack(CAPACITY);
        long syncTime = runBenchmark(syncStack, null);
        System.out.printf("🔹 Synchronized Array Stack Time: %,d ms%n", syncTime);

        // Test 2: Lock-Free CAS Stack
        LockFreeStack casStack = new LockFreeStack(CAPACITY);
        long casTime = runBenchmark(null, casStack);
        System.out.printf("🚀 Lock-Free CAS Stack Time:      %,d ms%n", casTime);
        
        // Calculate Improvement Factor
        double speedup = (double) syncTime / casTime;
        System.out.printf("%n🏆 CAS Stack is %.2fx faster under heavy contention!%n", speedup);
    }

    private static long runBenchmark(FixedSizeArrayStack syncStack, LockFreeStack casStack) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await(); // Synchronize thread startup
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        if (syncStack != null) {
                            syncStack.push(j);
                        } else {
                            casStack.push(j);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        long startTime = System.currentTimeMillis();
        startLatch.countDown(); // Release all threads at once!
        endLatch.await();       // Wait for all threads to finish
        long endTime = System.currentTimeMillis();

        executor.shutdown();
        return (endTime - startTime);
    }
}
