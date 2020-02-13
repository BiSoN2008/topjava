package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;


public class MealRepositoryInMemoryImpl implements MealRepositoryInMemory {
    private static Map<Integer,Meal> mealMap = new ConcurrentHashMap<>();
    public static final AtomicInteger idMeal = new AtomicInteger(0);
    private static final int DEFAULT_CALORIES_PER_DAY = 2000;
    static{
        mealMap.put(1,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealMap.put(2,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealMap.put(3,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealMap.put(4,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealMap.put(5,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealMap.put(6,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealMap.put(7,new Meal(idMeal.incrementAndGet(),LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public void deleteById(int Id){
        mealMap.remove(Id);
    }

    @Override
    public Meal save(Meal meal) {
        int id = idMeal.incrementAndGet();
        return mealMap.put(id,new Meal(meal.getId(),meal.getDateTime(),meal.getDescription(),meal.getCalories()));
    }

    @Override
    public Meal update(Meal meal, int id) {
        return null;
    }

    @Override
    public Map<Integer,MealTo> getList() {
        List<Meal> mealList = new ArrayList<>();
        for (Map.Entry<Integer, Meal> entry :mealMap.entrySet()) {
            mealList.add(entry.getValue());
        }

        Map<Integer,MealTo> resultMap = new ConcurrentHashMap<>();
        List<MealTo> mealToList = filteredByStreams(mealList, LocalTime.MIN,LocalTime.MAX,DEFAULT_CALORIES_PER_DAY);
        for(MealTo mealTo:mealToList)
        {
            resultMap.put(mealTo.getId(),mealTo);
        }
        return resultMap;
    }



}
