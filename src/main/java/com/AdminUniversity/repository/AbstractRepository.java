package com.AdminUniversity.repository;

import com.AdminUniversity.DTO.AbstractUser;
import com.AdminUniversity.DTO.Identifiable;

import java.util.*;

public abstract class AbstractRepository<U extends Identifiable> {
    private final LinkedHashMap<Integer, U> database;
    public AbstractRepository() {
        database = new LinkedHashMap<>();
    }
    public U save(U user) {
        if (user.getId()==null) {
            // Es un usuario nuevo, se le asigna id
            int id = getLastId()+1;
            user.setId(id);
            database.put(id, user);
        } else {
            database.put(user.getId(), user);
        }

        return user;
    }
    public void delete(U user) {
        database.remove(user.getId());
    }

    public void delete(int id) {
        database.remove(id);
    }

    public U getById(int id) {
        return database.get(id);
    }

    public List<U> getDB() {
        return new ArrayList<>(database.values());
    }

    private int getLastId() {
        int lastId = 0;
        Iterator<Map.Entry<Integer, U>> iterator = database.entrySet().stream().iterator();
        while (iterator.hasNext()) {
            lastId = iterator.next().getKey();
        }

        return lastId;
    }

}
