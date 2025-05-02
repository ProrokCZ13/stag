package pro1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pro1.apiDataModel.Specialization;
import pro1.apiDataModel.SpecializationsList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        Gson gson = new Gson();

        SpecializationsList response = gson.fromJson(json, SpecializationsList.class);
        List<Specialization> specializations = response.specializations;

        SimpleDateFormat parser = new SimpleDateFormat("d.M.yyyy");

        return specializations.stream()
                .filter(s -> s.deadline != null && s.deadline.value != null)
                .map(s -> s.deadline.value)
                .distinct()
                .sorted((a, b) -> {
                    try {
                        return parser.parse(a).compareTo(parser.parse(b));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(","));
    }
}