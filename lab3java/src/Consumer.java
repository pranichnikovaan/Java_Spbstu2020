import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
    private final BlockingQueue<Student> queue;
    private final int POISON;
    private final String subj;
    private final Object monitor;

    Consumer(BlockingQueue<Student> q, String subject, Object mon) {
        queue = q;
        POISON = -1;
        subj = subject;
        monitor = mon;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Student take;
                synchronized (monitor) {
                    while (queue.isEmpty()) {
                        System.out.println("Robot waiting");
                        monitor.wait();
                    }
                    take = queue.take();
                    System.out.println("Robot notifying");
                    monitor.notifyAll();
                }
                    if (take.getOrder() == POISON) {
                        break;
                    }
                    consume(take);
                }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void consume(Student take) throws InterruptedException {
        for (int i = 1; i <= take.getLabsCount() / 5; i++) {
            System.out.println("   Преподаватель по " + subj + " принял " + i * 5 + "/" + take.getLabsCount() + " лаб по " + take.getSubjectName() + " от студента № " + take.getOrder());
            Thread.sleep(200);
        }

    }
}
