import java.util.Random;

class Student {
    private int labsCount;
    private String subjectName;
    private int order;
    Student(int i) {
        order = i;
        int[] labs = {10, 20, 100};
        String[] subjects = {"ООП", "Физика", "Математика"};
        Random ran = new Random(System.currentTimeMillis());
        labsCount = labs[ran.nextInt(3)];
        subjectName = subjects[ran.nextInt(3)];
    }
    Student(int i, String sub)
    {
        order = i;
        labsCount = 0;
        subjectName = sub;
    }
    int getLabsCount() {
        return labsCount;
    }
    String getSubjectName() {
        return subjectName;
    }
    int getOrder() {
        return order;
    }
}
