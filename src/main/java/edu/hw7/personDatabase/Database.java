package edu.hw7.personDatabase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database implements PersonDatabase {
    private final Map<Integer, Person> database;
    private final Map<String, Set<Integer>> names;
    private final Map<String, Set<Integer>> addresses;
    private final Map<String, Set<Integer>> phoneNumbers;

    private final ReadWriteLock lock;

    public Database() {
        database = new HashMap<>();
        names = new HashMap<>();
        addresses = new HashMap<>();
        phoneNumbers = new HashMap<>();
        lock = new ReentrantReadWriteLock(true);
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (person.name() != null && person.address() != null && person.phoneNumber() != null) {
                Person check = database.get(person.id());
                if (check != null) {
                    deleteFromMap(names, check.name(), check.id());
                    deleteFromMap(addresses, check.address(), check.id());
                    deleteFromMap(phoneNumbers, check.phoneNumber(), check.id());
                }
                database.put(person.id(), person);

                putInMap(names, person.name(), person.id());
                putInMap(addresses, person.address(), person.id());
                putInMap(phoneNumbers, person.phoneNumber(), person.id());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = database.get(id);
            if (person == null) {
                return;
            }

            database.remove(person.id());
            deleteFromMap(names, person.name(), person.id());
            deleteFromMap(addresses, person.address(), person.id());
            deleteFromMap(phoneNumbers, person.phoneNumber(), person.id());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return getFromMap(names, name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return getFromMap(addresses, address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        lock.readLock().lock();
        try {
            return getFromMap(phoneNumbers, phone);
        } finally {
            lock.readLock().unlock();
        }
    }

    private <K, V> void putInMap(Map<K, Set<V>> map, K key, V value) {
        map.putIfAbsent(key, new HashSet<>());
        map.get(key).add(value);
    }

    private <K, V> void deleteFromMap(Map<K, Set<V>> map, K key, V value) {
        map.get(key).remove(value);
    }

    private <K, V> List<Person> getFromMap(Map<K, Set<V>> map, K key) {
        lock.readLock().lock();
        Set<V> found = map.get(key);
        if (found == null) {
            return List.of();
        }
        return found.stream()
            .map(database::get)
            .toList();
    }
}
