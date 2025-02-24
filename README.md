## Gridnine Flights Filter

### Содержание

- [Введение](#введение)
- [Использование фильтра](#использование-фильтра)
- [Список предикатов](#список-предикатов)
- [Создание нового предиката](#создание-нового-предиката)

### Введение

- Данный модуль позволяет фильтровать список перелетов по определенным правилам. В модуле имеется набор
  предикатов, которые реализуют определенные правила фильтрации
- Предикаты можно логически комбинировать или делать отрицание
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
- создайте объект класса FlightFilter из пакета com.gridnine.testing.filter, в конструктор подайте
  исходный список перелетов
- вызовите метод doFilter() класса FlightFilter, в качестве аргумента подайте комбинированный предикат
- получите отфильтрованный список, вызвав метод getFiltered() класса FlightFilter

#### Пример кода

```java     
// Создаем предикат, пропускающий только перелеты с датой и временем вылета
// позже текущей даты
LocalDateTime targetDateTime = LocalDateTime.now();
AbstractPredicate<Flight> predicate1 = new DeparturePredicate(Operator.GREATER_THAN, targetDateTime);

// Создаем предикат, пропускающий только те перелеты, в которых количество промежуточных
// рейсов больше или равно 3
long targetSegmentNumber = 3;
AbstractPredicate<Flight> predicate2 = new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, targetSegmentNumber);

// Делаем инверсию второго предиката
AbstractPredicate<Flight> invPredicate2 = predicate2.negate();

// Создаем предикат, пропускающий только те перелеты, у которых общее время в пути меньше 6 часов
long targetFlightMinutes = 360;
AbstractPredicate<Flight> predicate3 = new FlightDurationPredicate(Operator.LESS_THAN, targetFlightMinutes);

// Создаем комбинированый предикат, пропускающий только те перелеты, у которых дата и время
// вылета позже текущей даты, количество промежуточных рейсов меньше 3 и общее время в пути
// меньше 6 часов
AbstractPredicate<Flight> combinedPredicate = predicate1.and(invPredicate2).and(predicate3);

// Создаем объект класса FlightFilter, вызываем метод doFilter() с комбинированным предикатом,
// получаем отфильтрованный список
List<Flight> filtered = new FlightFilter(flights).doFilter(combinedPredicate).getFiltered();
```

Можно вызывать методы филтрации последовательно, при этом предикаты будут комбинироваться по правилу "и".
В этом случае код значительно сокращается

```java
// Сокращенная запись предыдущего кода
List<Flight> filtered = new FlightFilter(flights)
        .doFilter(new DeparturePredicate(Operator.GREATER_THAN, LocalDateTime.now()))
        .doFilter(new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, 3).negate())
        .doFilter(new FlightDurationPredicate(Operator.LESS_THAN, 360))
        .getFiltered();
```

Можно получать промежуточный результат работы фильтра

```java
// Получение промежуточного результата
Filter<Flight> filter = new FlightFilter(flights);

// В этом списке будут перелеты с датой и временем вылета позже текущей даты
List<Flight> filtered1 = filter
        .doFilter(new DeparturePredicate(Operator.GREATER_THAN, LocalDateTime.now()))
        .getFiltered();

// В этом списке будут перелеты, у которых дата и время вылета позже
// текущей даты и количество промежуточных рейсов меньше 3
List<Flight> filtered2 = filter
        .doFilter(new SegmentNumberPredicate(Operator.GREATER_THAN_OR_EQUAL_TO, 3).negate())
        .getFiltered();
```

### Список предикатов

- **ArrivalBeforeDeparturePredicate:** возвращает перелеты у которых время отправления позже, чем время
  прибытия.  
  *Параметры конструктора:* нет
- **DeparturePredicate:** возвращает перелеты у которых дата и время отправления раньше, позже или равно
  заданному в зависимости от оператора.  
  *Параметры конструктора:* operator:Operator - оператор, по которому происходит сравнение,
  targetDateTime:LocalDateTime - дата и время для сравнения
- **ArrivalPredicate:** возвращает перелеты у которых время прибытия раньше, позже или равно
  заданному в зависимости от оператора.  
  *Параметры конструктора:* operator:Operator - оператор, по которому происходит сравнение,
  targetDateTime:LocalDateTime - дата и время для сравнения
- **DepartureBetweenPredicate:** возвращает перелеты у которых дата и время отправления в заданном
  диапазоне.  
  *Параметры конструктора:* startDateTime:LocalDateTime - начало диапазона,
  endDateTime:LocalDateTime - конец диапазона
- **FlightDurationPredicate:** возвращает перелеты у которых общее время в пути
  больше, меньше или равно заданному в зависимости от оператора
  *Параметры конструктора:* operator:Operator - оператор, по которому происходит сравнение,
  targetMinutes:long - заданное время в минутах
- **GroundTimePredicate:** возвращает перелеты у которых общее время, проведенное на
  земле больше, меньше или равно заданному в зависимости от оператора.  
  *Параметры конструктора:* operator:Operator - оператор, по которому происходит сравнение,
targetMinutes:long - заданное время в минутах
- **SegmentNumberPredicate:** возвращает перелеты у которых количество промежуточных
  рейсов больше, меньше или равно заданному в зависимости от оператора.  
  *Параметры конструктора:* operator:Operator - оператор, по которому происходит сравнение,
  targetSegmentNumber:long - заданное количество промежуточных рейсов

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

    // Создайте поля необходимые поля
    private final Operator operator;
    private final LocalDateTime targetDateTime;

    // Создайте конструктор для всех полей класса
    public CustomPredicate(Operator operator, LocalDateTime targetDateTime) {
        // Сделайте проверку параметров
        // Проверка параметров......
        this.operator = operator;
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
