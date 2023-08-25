package ra.impl;

import ra.color.Color;
import ra.entity.Student;

import java.io.*;
import java.util.*;

public class StudentImpl {
    static List<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Đọc dữ liệu từ file listStudent.txt
        List<Student> readData = readDataFromFile();

        do {
            System.out.println("********************Menu*********************");
            System.out.println("1. Nhập thông tin các sinh viên");
            System.out.println("2. Tính tuổi các sinh viên");
            System.out.println("3. Tính điểm trung bình và xếp loại sinh viên");
            System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("5. Thống kê sinh viên theo xếp loại sinh viên");
            System.out.println("6. Cập nhật thông tin sinh viên theo mã sinh viên");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thoát");
            try{
                System.out.print("Lựa chọn của bạn là : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        StudentImpl.inputData(scanner);
                        break;
                    case 2:
                        StudentImpl.calAge();
                        break;
                    case 3:
                        StudentImpl.calAvgRank();
                        break;
                    case 4:
                        StudentImpl.sortStudent();
                        break;
                    case 5:
                        StudentImpl.statisticByRank();
                        break;
                    case 6:
                        StudentImpl.displayData();
                        StudentImpl.updateStudent(scanner);
                        break;
                    case 7:
                        StudentImpl.searchStudentName(scanner);
                        break;
                    case 8:
                        StudentImpl.writeDataToFile(studentList);
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-8");
                }
            }catch (NumberFormatException nfe){
                System.err.println("Dữ liệu không phải là số, vui lòng nhập lại");
            }catch (Exception ex){
                System.err.println("Đã xảy ra trong quá  xử lý, vui lòng nhập lại");
            }
        }while (true);
    }
    public static void writeDataToFile(List<Student> studentList){
        // Khởi tạo đối tượng file để làm việc với file - tương đối
        File file = new File("listStudent.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // Khởi tạo đối tượng FileOutputStream từ file - Checked Exception
            fos = new FileOutputStream(file);
            //Khởi tạo đối tượng ObjectOutputStream từ fos
            oos = new ObjectOutputStream(fos);
            //Sử dụng writeObject để ghi object ra file
            oos.writeObject(studentList);
            //2.5. Đẩy dữ liệu từ Stream xuống file
            oos.flush();
        }catch (FileNotFoundException ex1){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"File không tồn tại");
        }catch (IOException ex2){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Lỗi khi ghi dữ liệu ra file");
        }catch (Exception ex){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        }finally {
            //Đóng các stream
            try {
                if (oos != null){
                    oos.close();
                }
                if (fos != null){
                    fos.close();
                }
            }catch (IOException ex1){
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi khi đóng các stream");
            }catch (Exception ex2){
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
    }
    public static List<Student> readDataFromFile(){
        //Khởi tạo đối tượng File
        File file = new File("listStudent.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Student> studentListRead = null;
        try {
            // Khởi tạo đối tượng FileInputStream
            fis = new FileInputStream(file);
            //3. Khởi tạo đối tượng ObjectInputStream
            ois = new ObjectInputStream(fis);
            //4. Đọc dữ liệu object từ file (readObject())
            studentListRead = (List<Student>) ois.readObject();
        }catch (FileNotFoundException ex1){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"File không tồn tại");
        }catch (IOException ex2){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Lỗi khi đọc dữ liệu từ file");
        }catch (Exception ex){
            System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi trong quá trình đọc dữ liệu từ file");
        }finally {
            //Đóng các stream
            try {
                if (fis != null){
                    fis.close();
                }
                if (ois != null){
                    ois.close();
                }
            }catch (IOException ex1){
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi khi đóng các stream");
            }catch (Exception ex){
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
        return studentListRead;
    }
    // 1. Nhập thông tin các sinh viên
    public static void inputData(Scanner scanner){
        System.out.print("Nhập vào số sinh viên cần nhập dữ liệu :");
        do {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    Student student = new Student();
                    student.inputData(scanner, studentList);
                    studentList.add(student);
                }
                break;
            } catch (NumberFormatException nfe) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Dữ liệu không phải là số thực, vui lòng nhập lại");
            }catch (Exception ex){
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Đã xảy ra trong quá  xử lý, vui lòng nhập lại");
            }
        } while (true);

    }
    // 2. Tính tuổi các sinh viên
    public static void calAge(){
        for (Student student : studentList) {
            student.calAge();
        }
        System.out.println("Đã tính xong tuổi cho tất cả sinh viên");
    }
    // 3. Tính điểm trung bình và xếp loại sinh viên
    public static void calAvgRank(){
        for (Student student : studentList) {
            student.calAvgMark_Rank();
        }
        System.out.println("Đã tính xong điểm trung bình và xếp loại cho tất cả các sinh viên");
    }
    // 4. Sắp xếp sinh viên theo tuổi tăng dần
    public  static void sortStudent(){
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("Sắp xếp sinh viên theo tuổi tăng dần là :");
        for (Student student : studentList) {
            student.displayData();
        }
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }
    // 5. Thống kê sinh viên theo xếp loại sinh viên
    public static void statisticByRank(){
        // Sử dụng 1 mảng để đếm số lượng sinh viên theo từng xếp loại
        int[] classifyNumber = new int[5]; // Yếu, Trung bình, Khá, Giỏi, Xuất Sắc
        for (Student student : studentList) {
            // Chuỗi lưu trữ xếp loại sinh viên được trả về từ phương thức getRate()
            String rank = student.getRank();
            // Kiểm tra giá trị của biến rate để xác định xếp loại của sinh viên và tăng giá trị của phần tử tương ứng trong mảng
            if (rank.equals("Yếu")){
                classifyNumber[0]++;
            } else if (rank.equals("Trung bình")) {
                classifyNumber[1]++;
            }else if (rank.equals("Khá")) {
                classifyNumber[2]++;
            }else if (rank.equals("Giỏi")) {
                classifyNumber[3]++;
            }else if (rank.equals("Xuất sắc")) {
                classifyNumber[4]++;
            }
        }
        // In thống kê
        System.out.println("Thống kê sinh viên theo xếp loại");
        System.out.println("Yếu : " + classifyNumber[0]);
        System.out.println("Trung bình : " + classifyNumber[1]);
        System.out.println("Khá : " + classifyNumber[2]);
        System.out.println("Giỏi : " + classifyNumber[3]);
        System.out.println("Xuất sắc : " + classifyNumber[4]);
    }
    // 6. Cập nhật thông tin sinh viên theo mã sinh viên
    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật:");
        String studentId = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(studentId)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            //Cập nhật
            studentList.get(index).setStudentName(Student.validateStudentName(scanner));
            studentList.get(index).setSex(Student.validateSex(scanner));
            studentList.get(index).setBirthday(Student.validateBirthday(scanner));
            studentList.get(index).setMark_html(Student.validateMarkHTML(scanner));
            studentList.get(index).setMark_css(Student.validateMarkCSS(scanner));
            studentList.get(index).setMark_javascript(Student.validateMarkJavascript(scanner));
            studentList.get(index).calAge();
            studentList.get(index).calAvgMark_Rank();
        } else {
            System.out.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Không tồn tại mã sinh viên như vậy");
        }
    }
    // 7. Tìm kiếm sinh viên theo tên sinh viên
    public static void searchStudentName(Scanner scanner){
        System.out.println("Nhập vào tên sinh viên cần tìm: ");
        String studentName = scanner.nextLine();
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(studentName.toLowerCase())){
                student.displayData();
            }
        }
    }
    public static void displayData(){
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.printf("%-15s%-30s%-10s%-15s%-15s%-20s%-25s%-20s%-20s\n","Mã sinh viên", "Tên sinh viên" , "Tuổi", "Điểm html", "Điểm css", "Điểm javascript", "Điểm trung bình", "Giới tính", "Xếp loại");
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (Student student:studentList) {
            student.displayData();
        }

    }
}
