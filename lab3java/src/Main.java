import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args){
        Object monitor = new Object();
        HashMap<String, BlockingQueue<Student>> qs= new HashMap<>();
        qs.put("ООП", new LinkedBlockingQueue<>());
        qs.put("Физика", new LinkedBlockingQueue<>());
        qs.put("Математика", new LinkedBlockingQueue<>());
        new Thread(new Producer(qs, args[0], monitor)).start();
        new Thread(new Consumer(qs.get("ООП"), "ООП",monitor)).start();
        new Thread(new Consumer(qs.get("Физика"), "Физика", monitor)).start();
        new Thread(new Consumer(qs.get("Математика"), "Математика", monitor)).start();
    }
}
