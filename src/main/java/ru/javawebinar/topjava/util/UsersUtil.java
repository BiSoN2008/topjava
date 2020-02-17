package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(0, "Nikki", "nikki@gmail.com", "qwerty", Role.ROLE_USER),
            new User(0, "Jimmy", "jimmy@gmail.com", "qwerty", Role.ROLE_USER),
            new User(0, "Nasty", "nastya@gmail.com", "qwerty", Role.ROLE_USER),
            new User(0, "Pasha", "pasha@gmail.com", "qwerty", Role.ROLE_USER),
            new User(0, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN)
    );

    public static List<User> sortedNameList(List<User> list) {
        return list.stream()
                .sorted((Comparator.comparing(AbstractNamedEntity::getName)))
                .collect(Collectors.toList());
    }
}
