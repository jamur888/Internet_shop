import menu.AdminMenu;

import java.sql.SQLException;

/*
 *"Администратор осуществляет ведение каталога Товаров.
 *Клиент делает и оплачивает Заказ на Товары.
 *Администратор может занести неплательщиков в “черный список"
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        AdminMenu menu = AdminMenu.getInstance();
        menu.getMenu();

    }
}

