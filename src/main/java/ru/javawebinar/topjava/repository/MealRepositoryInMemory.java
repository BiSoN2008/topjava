package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;
import java.util.Map;

public interface MealRepositoryInMemory {
        void deleteById(int Id);
        Meal save(Meal meal);
        Meal update(Meal meal,int id);
        Map<Integer,MealTo> getList();

}
