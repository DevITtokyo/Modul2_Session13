package ra.entity;

import java.util.List;
import java.util.Scanner;

public interface IEntity<S> {
    void inputData(Scanner scanner, List<Student> studentList);
    void displayData();
    void calAge();
    void calAvgMark_Rank();
}
