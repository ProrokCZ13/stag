package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        String json1 = Api.getTeachersByDepartment(department);
        String json2 = Api.getActionsByDepartment(department, year);
        TeachersList teachersList = new Gson().fromJson(json1, TeachersList.class);
        ActionsList actionsList = new Gson().fromJson(json2, ActionsList.class);
        Teacher bestTeacher = teachersList.items.stream().max(Comparator.comparing(t -> TeacherScore(t.id, actionsList))).get();
        return bestTeacher.email;

        // TODO 3.2:
        //  - Stáhni seznam učitelů na katedře
        //  - Stáhni seznam akcí na katedře
        //  - Najdi učitele s nejvyšším "score" a vrať jeho e-mail

    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return departmentSchedule.items.stream().filter(a -> a.teacherId == teacherId).mapToInt(a -> a.personsCount).sum();}
}