public class DeliveryService {
    public static void main(String[] args) {
        Map<Address, Double> costPerAddress = new HashMap<>();

        // Создаем адреса и заполняем карту цен
        Address address1 = new Address("USA", "New York");
        Address address2 = new Address("Germany", "Berlin");
        Address address3 = new Address("Japan", "Tokyo");

        costPerAddress.put(address1, 10.0);
        costPerAddress.put(address2, 12.5);
        costPerAddress.put(address3, 15.0);

        Scanner scanner = new Scanner(System.in);
        double totalCost = 0;

        System.out.println("Введите заказы в формате 'страна город вес', или 'end' для завершения:");
        while (true) {
            String input = scanner.nextLine();
            if ("end".equalsIgnoreCase(input)) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Некорректный формат ввода. Попробуйте снова.");
                continue;
            }

            String country = parts[0];
            String city = parts[1];
            double weight;
            try {
                weight = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный вес. Попробуйте снова.");
                continue;
            }

            Address orderAddress = new Address(country, city);
            if (costPerAddress.containsKey(orderAddress)) {
                double cost = costPerAddress.get(orderAddress) * weight;
                totalCost += cost;
                System.out.printf("Стоимость доставки в %s составляет: %.2f руб.\n", orderAddress, cost);
            } else {
                System.out.printf("Адрес %s не обслуживается.\n", orderAddress);
            }
        }

        System.out.printf("Общая стоимость всех доставок: %.2f руб.\n", totalCost);
        scanner.close();
    }
}
