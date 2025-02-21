## Gridnine Flights Filter Module

### Содержание

- [Введение](#введение)
- [Использование фильтра](#использование-фильтра)
- [Создание нового предиката](#создание-нового-предиката)

### Введение

Данный модуль позволяет фильтровать список перелетов по определенным правилам. В модуле имеется набор
предикатов, которые реализуют определенные правила фильтрации. Также, при необходимости,
можно добавить новый предикат. Предикаты можно логически комбинировать или сделать отрицание.

### Использование фильтра

- создайте один или несколько классов-предикатов из пакета com.gridnine.testing.predicate.impl
- создайте комбинированный предикат, согласно заданным правилам фильтрации
- предикаты объединяются по правилам и/или с помощью методов and()/or()
- для инверсии предиката можно использовать метод negate()
- создайте объект класса FlightFilter из пакета com.gridnine.testing.filter, в конструктор подайте исходный список перелетов
- вызовите метод doFilter() в классе FlightFilter, в качестве аргумента подайте комбинированный предикат
- метод doFilter() возвращает отфильтрованный список

#### Пример кода

```java     

// Создаем предикат, пропускающий только перелеты с датой и временем вылета
// позже текущей даты
LocalDateTime targetDateTime = LocalDateTime.now();
FlightPredicate predicate1 = new DepartureAfterTargetPredicate(targetDateTime);

// Создаем предикат, пропускающий только те перелеты, в которых количество промежуточных
// рейсов больше или равно 3
long targetSegmentNumber = 3;
FlightPredicate predicate2 = new SegmentNumberGreaterOrEqualsThanTargetPredicate(targetSegmentNumber);

// Создаем комбинированый предикат, пропускающий только те перелеты, в которых дата и время
// вылета позже текущей даты и количество промежуточных рейсов меньше 3
FlightPredicate combinedPredicate = predicate1.and(predicate2.negate());

// Создаем фильтр. Подаем в конструктор исходный массив перелетов
FlightFilter filter = new FlightFilter(flights);

// Вызываем метод doFilter(), подаем в него предикат и получаем отфильтрованный список
List<Flight> filtered = filter.doFilter(combinedPredicate);
```

### Создание нового предиката

- создайте класс-наследник от абстрактного класса FlightPredicate из пакета com.gridnine.testing.predicate
- опрелелите в классе поля для значений, которые будут применяться в логике фильтрации
- создайте конструктор с параметрами для всех полей класса
- создайте геттеры, если необходимо
- реализуйте абстрактный метод test() из родительского класса

#### Пример кода

```java

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

// Новый предикат с пользовательской логикой
public class CustomPredicate extends FlightPredicate {

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
```
