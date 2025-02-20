
## Gridnine Flights Filter Module

###  Содержание
- [Введение](#введение)
- [Использование фильтра](#использование-фильтра)
- [Создание нового предиката](#создание-нового-предиката)

###  Введение

Данный модуль позволяет фильтровать список перелетов по определенным правилам. За один раз можно
применить несколько правил. В модуле имеется набор предикатов, которые реализуют определенные правила
фильтрации. Также при необходимости имеется возможность добавить новый предикат.


### Использование фильтра
- создайте необходимые предикаты
- содайте объект класса FlightFilter из пакета com.gridnine.testing в конструктор подайте список полетов для фильтрации
- вызовите метод doFilter() в классе FlightFilter для каждого предиката
- получите отфильтрованный список, вызвав метод getResult() в классе FlightFilter

####  Пример кода

```java     
@Test
void filter_implementation_example() {
    FlightPredicate predicate1 = (f) -> true;
    FlightPredicate predicate2 = (f) -> true;
    FlightPredicate predicate3 = (f) -> true;

    FlightFilter filter = new FlightFilter(flights);

    List<Flight> filtered = filter
            .doFilter(predicate1)
            .doFilter(predicate2)
            .doFilter(predicate3)
            .getResult();

    System.out.println(filtered);
}
```


### Создание нового предиката
- создайте новый класс
- имплементируйте интерфейс FlightPredicate из пакета com.gridnine.testing.predicate
- опрелелите в классе поля, которые будут содержать значения, которые применяются в алгоритме фильтрации 
- создайте конструктор с параметрами для всех полей класса
- реализуйте метод test() из интерфейса Predicate

####  Пример кода

