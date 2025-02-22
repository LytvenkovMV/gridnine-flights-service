## Gridnine Flights Filter

### Содержание

- [Введение](#введение)
- [Использование фильтра](#использование-фильтра)
- [Список предикатов](#список-предикатов)
- [Создание нового предиката](#создание-нового-предиката)

### Введение

- Данный модуль позволяет фильтровать список перелетов по определенным правилам. В модуле имеется набор
предикатов, которые реализуют определенные правила фильтрации 
- Предикаты можно логически комбинировать или сделать отрицание
- При необходимости, можно добавить новый предикат 
- Имеется возможность последовательно вызывать методы фильтрации и получать промежуточные
результаты. 
*Важное замечание: для того, чтобы иметь эту возможность, в классе
FlightFilter есть массив флагов, который хранит результат работы каждого фильтра.
Такое решение делает фильтр более гибким и универсальным, хотя и приводит к
некоторому дополнительному расходу памяти*

### Использование фильтра

- создайте один или несколько классов-предикатов из пакета com.gridnine.testing.predicate.impl
- создайте комбинированный предикат, согласно заданным правилам фильтрации
- предикаты объединяются по правилам и/или с помощью методов and()/or()
- для инверсии предиката можно использовать метод negate()
- создайте объект класса FlightFilter из пакета com.gridnine.testing.filter, в конструктор подайте исходный список перелетов
- вызовите метод doFilter() класса FlightFilter, в качестве аргумента подайте комбинированный предикат
- получите отфильтрованный список, вызвав метод get() класса FlightFilter

#### Пример кода

```java     
// Создаем предикат, пропускающий только перелеты с датой и временем вылета
// позже текущей даты
LocalDateTime targetDateTime = LocalDateTime.now();
AbstractPredicate<Flight> predicate1 = new DepartureAfterPredicate(targetDateTime);

// Создаем предикат, пропускающий только те перелеты, в которых количество промежуточных
// рейсов больше или равно 3
long targetSegmentNumber = 3;
AbstractPredicate<Flight> predicate2 = new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber);

// Создаем комбинированный предикат, пропускающий только те перелеты, в которых дата и время
// вылета позже текущей даты и количество промежуточных рейсов меньше 3
AbstractPredicate<Flight> combinedPredicate = predicate1.and(predicate2.negate());

// Создаем объект класса FlightFilter, вызываем метод doFilter() с комбинированным предикатом,
// получаем отфильтрованный список
List<Flight> filtered = new FlightFilter(flights).doFilter(combinedPredicate).get();
```

Можно вызывать фильтры по цепочке, при этом предикаты будут комбинироваться по правилу "и".
В этом случае код значительно сокращается

```java
// Сокращенная запись предыдущего кода
LocalDateTime targetDateTime = LocalDateTime.now();
long targetSegmentNumber = 3;

List<Flight> filtered = new FlightFilter(flights)
        .doFilter(new DepartureAfterPredicate(targetDateTime))
        .doFilter(new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber).negate())
        .get();
```

Можно получать промежуточный результат работы фильтра

```java
// Получение промежуточного результата
LocalDateTime targetDateTime = LocalDateTime.now();
long targetSegmentNumber = 3;

Filter<Flight> filter = new FlightFilter(flights);

// В этом списке будут перелеты с датой и временем вылета позже текущей даты
List<Flight> filtered1 = filter
        .doFilter(new DepartureAfterPredicate(targetDateTime))
        .get();

// В этом списке будут перелеты, у которых дата и время вылета позже
// текущей даты и количество промежуточных рейсов меньше 3
List<Flight> filtered2 = filter
        .doFilter(new SegmentNumberGreaterOrEqualsPredicate(targetSegmentNumber).negate())
        .get();
```

### Список предикатов

- **ArrivalBeforeDeparturePredicate:** возвращает перелеты у которых время отправления позже, чем время
прибытия.  
*Параметры конструктора:* нет
- **DepartureAfterPredicate:** возвращает перелеты у которых время отправления позже
чем заданное время.  
*Параметры конструктора:* targetDateTime:LocalDateTime - время для сравнения
- **DepartureBetweenPredicate:** возвращает перелеты у которых время отправления в заданном
    диапазоне.  
*Параметры конструктора:* startDateTime:LocalDateTime - начало диапазона,
  endDateTime:LocalDateTime - конец диапазона
- **FlightDurationExceedsPredicate:** возвращает перелеты у которых общее время в пути
превышает заданное.  
*Параметры конструктора:* targetMinutes:long - заданное время в минутах
- **GroundTimeExceedsPredicate:** возвращает перелеты у которых общее время, проведенное на
земле превышает заданное.  
*Параметры конструктора:* targetMinutes:long - заданное время в минутах
- **SegmentNumberGreaterOrEqualsPredicate:** возвращает перелеты у которых количество промежуточных
рейсов больше или равно заданному.  
*Параметры конструктора:* targetSegmentNumber:long - заданное
количество промежуточных рейсов

### Создание нового предиката

- создайте класс-наследник от абстрактного класса AbstractPredicate из пакета com.gridnine.testing.predicate
- опрелелите в классе поля для значений, которые будут применяться в логике фильтрации
- создайте конструктор с параметрами для всех полей класса
- создайте геттеры, если необходимо
- реализуйте абстрактный метод test() из родительского класса

#### Пример кода

```java
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

// Новый предикат с пользовательской логикой
public class CustomPredicate extends AbstractPredicate<Flight> {

    // Создайте поля, содержащие целевые значения
    private final long targetNumber;
    private final LocalDateTime targetDateTime;

    // Создайте конструктор для всех полей класса
    public CustomPredicate(long targetNumber, LocalDateTime targetDateTime) {
        // Сделайте проверку параметров
        // Проверка параметров......
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
