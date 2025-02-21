package com.gridnine.testing.predicate;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

// Новый предикат с пользовательской логикой
public class CustomPredicate extends AbstractPredicate<Flight> {

    // Создайте поля, содержащие целевые значения
    private final long targetNumber;
    private final LocalDateTime targetDateTime;

    // Создайте конструктор для всех полей класса
    public CustomPredicate(long targetNumber, LocalDateTime targetDateTime) {
        this.targetNumber = targetNumber;
        this.targetDateTime = targetDateTime;
    }

    // Создайте геттеры, если необходимо
    // getters......

    // Реализуйте абстрактный метод из родительского класса
    @Override
    public boolean test(Flight f) {

        // Напишите логику фильтрации
        // и верните результат

        return false;
    }
}