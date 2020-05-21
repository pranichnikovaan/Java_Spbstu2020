import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private final HashMap<String, BlockingQueue<Student>> queues;
    private final int POISON;
    private final int count;
    private final Object monitor;

    Producer(HashMap<String, BlockingQueue<Student>> q, String param, Object mon) {
        POISON = -1;
        count = Integer.parseInt(param);
        queues = q;
        monitor = mon;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void produce() throws InterruptedException {
        int i = 1;
        while (i <= count) {
            synchronized (monitor) {
                System.out.println(queues.get("ООП").size() + queues.get("Физика").size() + queues.get("Математика").size());
                while (queues.get("ООП").size() + queues.get("Физика").size() + queues.get("Математика").size() >= 10) {
                    System.out.println("Producer waiting");
                    monitor.wait();
                }
                Student student = new Student(i);
                System.out.println("Студент № " + i + " с " + student.getLabsCount() + " лабами по " + student.getSubjectName() + " встал в очередь");
                queues.get(student.getSubjectName()).put(student);
                monitor.notifyAll();
                System.out.println("Producer notifying");
                i++;
            }
        }

        Student student1 = new Student(POISON, "ООП");
        queues.get("ООП").put(student1);
        Student student2 = new Student(POISON, "Физика");
        queues.get("Физика").put(student2);
        Student student3 = new Student(POISON, "Математика");
        queues.get("Математика").put(student3);
    }
}
