package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

//        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        mealsTo.forEach(System.out::println);
        filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        Map<LocalDate, Integer> caloriesDays = new HashMap<>();
        for (UserMeal list : meals) {
            caloriesDays.merge(list.getDate(),list.getCalories(),Integer::sum);
        }
        List<UserMealWithExcess> filteredList = new ArrayList<>();
        for (UserMeal list : meals) {
            LocalTime time = list.getDateTime().toLocalTime();
            if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
                if (caloriesDays.get(list.getDateTime().toLocalDate()) <= caloriesPerDay)
                    filteredList.add(new UserMealWithExcess(list.getDateTime(), list.getDescription(), list.getCalories(), false));
                else
                    filteredList.add(new UserMealWithExcess(list.getDateTime(), list.getDescription(), list.getCalories(), true));
            }
        }
        return filteredList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Integer> map = meals.stream()
                .collect(Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum));
//        List<UserMealWithExcess> filteredList = meals
//                .stream()
//                .filter(m ->m.getTime().compareTo(startTime) >=0 && m.getTime().compareTo(endTime) <=0)
//                .map(UserMeal::getDate)
//                .collect(toList(createUserMealsExcess(toList(),true)));

        return null;
    }

    public static UserMealWithExcess createUserMealsExcess(UserMeal userMeal,boolean excess){
        return new UserMealWithExcess(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),excess);
    }
}

