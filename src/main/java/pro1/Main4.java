package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;
import java.util.List;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static List<Teacher> printShortestEmails(String department, int count) {

        String json = Api.getTeachersByDepartment(department);
        TeachersList list = new Gson().fromJson(json, TeachersList.class);

        list.items.stream().filter (t -> t.email!=null).sorted(Comparator.comparing(t -> t.email.length())).limit(count).forEach(t -> System.out.println(t.email));

        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
        return list.items;
    }
}