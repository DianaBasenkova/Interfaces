import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    private Ticket ticket1 = new Ticket("Москва", "Симферополь", 1, 0, 1);//1 час
    private Ticket ticket2 = new Ticket("Москва", "Абакан", 2, 23, 6);
    private Ticket ticket3 = new Ticket("Москва", "Адлер", 5, 11, 13);
    private Ticket ticket4 = new Ticket("Москва", "Симферополь", 4, 1, 3);//2 часа
    private Ticket ticket5 = new Ticket("Москва", "Симферополь", 5, 2, 5);//3 часа
    private Ticket ticket6 = new Ticket("Москва", "Симферополь", 6, 7, 9);//2 часа

    @Test
    public void shouldSearchIfNotFind() {
        //поиск если нет совпадений
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchAndSort("Москва", "Новый Уренгой");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        //поиск и сортировка по цене, от меньшей к большей
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        Ticket[] expected = new Ticket[]{ticket1, ticket4, ticket5, ticket6};
        Ticket[] actual = manager.searchAndSort("Москва", "Симферополь");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTime() {
        //поиск и сортировка по времени полета
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket4);
        manager.add(ticket5);
        Comparator<Ticket> comparator = new TicketTimeComparator();
        Ticket[] expected = new Ticket[]{ticket1, ticket4, ticket5};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Симферополь", comparator);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldCompareIfSamePrice() {
        //сравнение если цены одинаковые
        AviaSouls manager = new AviaSouls();

        int expected = 0;
        int actual = ticket5.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }
    }
