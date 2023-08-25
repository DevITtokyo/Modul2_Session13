package ra.entity;

import ra.color.Color;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Student implements IEntity<Student>, Serializable {
    // a. Các thuộc tính
    public String studentId;
    public String studentName;
    public Date birthday;
    public int age;
    public boolean sex;
    public float mark_html;
    public float mark_css;
    public float mark_javascript;
    public  float avgMark;
    public String rank;
    // Constructor
    public Student() {
    }
    public Student(String studentId, String studentName, Date birthday, int age, boolean sex, float mark_html, float mark_css, float mark_javascript, float avgMark, String rank) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.mark_html = mark_html;
        this.mark_css = mark_css;
        this.mark_javascript = mark_javascript;
        this.avgMark = avgMark;
        this.rank = rank;
    }
    // Getter and Setter
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public float getMark_html() {
        return mark_html;
    }
    public void setMark_html(float mark_html) {
        this.mark_html = mark_html;
    }
    public float getMark_css() {
        return mark_css;
    }
    public void setMark_css(float mark_css) {
        this.mark_css = mark_css;
    }
    public float getMark_javascript() {
        return mark_javascript;
    }
    public void setMark_javascript(float mark_javascript) {
        this.mark_javascript = mark_javascript;
    }
    public float getAvgMark() {
        return avgMark;
    }
    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    // Method
    // Validate studentId : mã sinh viên gồm 4 ký tự, bắt đầu là ký tự S và không trùng lặp
    public String validateStudentId(Scanner scanner, List<Student> studentList) {
        System.out.print("Nhập vào mã sinh viên : ");
        boolean checkStudentId = true;
        do {
            String studentId = scanner.nextLine();
            if (studentId.length() == 4) {
                if (studentId.startsWith("S")) {
                    boolean isStudentIdExist = false;
                    for (Student student : studentList) {
                        if (student.getStudentId().equals(studentId)) {
                            isStudentIdExist = true;
                            break;
                        }
                    }
                    if (!isStudentIdExist) {
                        return studentId;
                    } else {
                        System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD + "Mã sinh viên đã tồn tại, vui lòng nhập lại");
                    }
                } else {
                    System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD + "Mã sinh viên gồm 4 ký tự, vui lòng nhập lại");
                }
            }else {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD +"Mã sinh viên phải gồm 4 ký tự, vui lòng nhập lại");
            }
        } while (checkStudentId) ;
        return null;
    }
    // Validate studentName : tên sinh viên có từ 10-50 ký tự
    public static String validateStudentName (Scanner scanner){
        System.out.print("Nhập vào tên sinh viên : ");
        boolean checkStudentName = true;
        do {
            String studentName = scanner.nextLine();
            if (studentName.length() >=10 && studentName.length()<=50){
                return studentName;
            }else {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+ "Tên sinh viên gồm 10-50 ký tự, vui lòng nhập lại");
            }
        }while (checkStudentName);
        return null;
    }
    // Validate ngày sinh có năm sinh trước năm 2005
    public static Date validateBirthday (Scanner scanner){
        System.out.print("Nhập vào ngày sinh của sinh viên : ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            try {
                Date birthday = sdf.parse(scanner.nextLine());
                Calendar cal = Calendar.getInstance();
                cal.setTime(birthday);
                if (cal.get(Calendar.YEAR) < 2005) {
                    return birthday;
                } else {
                    System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Năm sinh phải trước năm 2005, vui lòng nhập lại");
                }
            } catch (ParseException ex1) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Ngày sinh phải có định dạng dd/MM/yyyy, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Xảy ra lỗi không xác định, vui lòng liên hệ với hệ thống");
            }
        } while (true);
    }
    // Validate giới tính sinh viên chỉ nhận true|false
    public static Boolean validateSex (Scanner scanner){
        System.out.print("Nhập vào giới tính của sinh viên : ");
        do {
            String sex = scanner.nextLine();
            if (sex.equalsIgnoreCase("true") || sex.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(sex);
            } else {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Giới tính chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }
    // Validate điểm html, css, js có giá trị trong khoảng 0-10
    public static float validateMarkHTML (Scanner scanner){
        System.out.print("Nhập vào điểm HTML của sinh viên : ");
        do {
            try {
                float mark_html=Float.parseFloat(scanner.nextLine());
                if (mark_html>=0 && mark_html<=10){
                    return mark_html;
                }else {
                    System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Điểm HTML giá trị trong khoảng 0-10, vui lòng nhập lại");
                }
            }catch (NumberFormatException nfe) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Dữ liệu không phải là số thực, vui lòng nhập lại");
            }catch (Exception ex) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Đã xảy ra trong quá  xử lý, vui lòng nhập lại");
            }
        }while (true);
    }
    public static float validateMarkCSS (Scanner scanner){
        System.out.print("Nhập vào điểm CSS của sinh viên : ");
        do {
            try {
                float mark_css=Float.parseFloat(scanner.nextLine());
                if (mark_css>=0 && mark_css<=10){
                    return mark_css;
                }else {
                    System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Điểm Css giá trị trong khoảng 0-10, vui lòng nhập lại");
                }
            }catch (NumberFormatException nfe) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Dữ liệu không phải là số thực, vui lòng nhập lại");
            }catch (Exception ex) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Đã xảy ra trong quá  xử lý, vui lòng nhập lại");
            }
        }while (true);
    }
    public static float validateMarkJavascript (Scanner scanner){
        System.out.print("Nhập vào điểm Javascript của sinh viên : ");
        do {
            try {
                float mark_javascript=Float.parseFloat(scanner.nextLine());
                if (mark_javascript>=0 && mark_javascript<=10){
                    return mark_javascript;
                }else {
                    System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Điểm Javascript giá trị trong khoảng 0-10, vui lòng nhập lại");
                }
            }catch (NumberFormatException nfe) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Dữ liệu không phải là số thực, vui lòng nhập lại");
            }catch (Exception ex) {
                System.err.println(Color.YELLOW_BACKGROUND + Color.BLACK_BOLD+"Đã xảy ra trong quá  xử lý, vui lòng nhập lại");
            }
        }while (true);
    }
    @Override
    public void inputData(Scanner scanner, List<Student> studentList) {
        this.studentId = validateStudentId(scanner, studentList);
        this.studentName = validateStudentName(scanner);
        this.birthday = validateBirthday(scanner);
        this.sex = validateSex(scanner);
        this.mark_html = validateMarkHTML(scanner);
        this.mark_css = validateMarkCSS(scanner);
        this.mark_javascript = validateMarkJavascript(scanner);
    }

    @Override
    public void displayData() {
        System.out.printf("StudentId: %s - Student Name: %s - BirthDay: %td-%tb-%tY - Age: %d\n", this.studentId, this.studentName, this.birthday,this.age);
        System.out.printf("Sex: %s - HTML: %f - CSS: %f - Javascript: %f - Avg Mark: %f - Rank: %s\n",this.sex, this.mark_html, this.mark_css, this.mark_javascript, this.avgMark, this.rank);
    }
    @Override
    public void calAge() {
        Date now = new Date();
        this.age = now.getYear() - this.birthday.getYear();
    }

    @Override
    public void calAvgMark_Rank() {
        this.avgMark = (mark_html + mark_css + mark_javascript) / 3;
        if (this.avgMark<5){
            this.rank = "Yếu";
        } else if (this.avgMark<7) {
            this.rank = "Trung bình";
        } else if (this.avgMark<8){
            this.rank = "Khá";
        } else if (this.avgMark<9){
            this.rank = "Giỏi";
        }else {
            this.rank = "Xuất sắc";
        }
    }
}
