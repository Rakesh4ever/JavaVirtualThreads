import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        var start = System.currentTimeMillis();
        var totalThread = 100000;
        var threads = IntStream.range(0, totalThread)
                .mapToObj(
                        thCount -> Thread.ofVirtual().unstarted(() -> {
                        })).toList();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        var end = System.currentTimeMillis();
        System.out.println("Millisecond used to launch " + totalThread + " VT-threads: " + (end - start) + " ms");
    // for Plateform thread :
        var pThreadStart = System.currentTimeMillis();
        var pThreads = IntStream.range(0, totalThread)
                .mapToObj(
                        thCount -> Thread.ofPlatform().unstarted(() -> {
                        })).toList();
        pThreads.forEach(Thread::start);
        for (Thread thread : pThreads) {
            thread.join();
        }
        var pThreadsEnd = System.currentTimeMillis();
        System.out.println("Milliseconds used to launch " + totalThread + " PT-threads: " + (pThreadsEnd - pThreadStart) + " ms");

    }
}