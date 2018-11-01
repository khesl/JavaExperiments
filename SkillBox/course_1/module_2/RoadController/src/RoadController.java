package course_1.module_2.RoadController.src;

import course_1.module_2.RoadController.src.core.*;

public class RoadController
{
    // Double passengerCarMaxWeight
    public static Double passengerCarMaxWeight = 3500.0; // kg
    // Integer passengerCarMaxHeight
    public static Integer passengerCarMaxHeight = 2000; // mm
    // Integer controllerMaxHeight
    public static Integer controllerMaxHeight = 4000; // mm
    // Integer passengerCarPrice
    public static Integer passengerCarPrice = 100; // RUB
    // Integer cargoCarPrice
    public static Integer cargoCarPrice = 250; // RUB
    // Integer vehicleAdditionalPrice
    public static Integer vehicleAdditionalPrice = 200; // RUB

    // Integer maxOncomingSpeed
    public static Integer maxOncomingSpeed = 30; // km/h
    // Integer speedFineGrade
    public static Integer speedFineGrade = 20; // km/h
    // Integer finePerGrade
    public static Integer finePerGrade = 500; // RUB
    // Integer criminalSpeed
    public static Integer criminalSpeed = 160; // km/h

    public static void main(String[] args)
    {
        // Integer i
        for(Integer i = 0; i < 10; i++) // в цикле описываем поведение для 10 повторений
        {
            // Car car
            Car car = Camera.getNextCar(); // создаём новый авто (генерируем)
            System.out.println(car);    // выводим в чат данные по методу toString() @see Car.java
            System.out.println("Скорость: " + Camera.getCarSpeed(car) + " км/ч"); // задаём машине скорость

            /**
             * Пропускаем автомобили спецтранспорта
             */
            if(car.isSpecial()) { // проверяем спец транспорт
                openWay(); // для них двери открыты
                continue; // пропускаем цикл
            }

            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            // Boolean policeCalled
            Boolean policeCalled = false; // обнуляем вызов копов
            for(String criminalNumber : Police.getCriminalNumbers()) // пробегаем по сформированному списку криминал. номеров
            {
                // String carNumber
                String carNumber = car.getNumber(); // получаем номер авто
                if(carNumber.equals(criminalNumber)) { // сравниваем номер по списку
                    Police.call("автомобиль нарушителя с номером " + carNumber); // вызываем копов, если совпало
                    blockWay("не двигайтесь с места! За вами уже выехали!"); // типо закрываем путь
                    break; // и идём дальше
                }
            }
            if(Police.wasCalled()) { // проверка на вызов копов
                continue; // должны делать break; т.к. уже звали копов
            }

            /**
             * Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
             */
            //Integer carHeight
            Integer carHeight = car.getHeight(); // получаем мвысотк авто
            // Integer price
            Integer price = 0; // и стоимость
            /**
             *  тут всё элементарно, проверяем по высоте потом по весу..
             *  так же как и штраф на проверку криминальной скорости, а потом не значительное превышение.
             * */

            if(carHeight > controllerMaxHeight) // проверка на максимум доступность по высоте
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue; // блокируем путь и идём к след итерации цикла
            }
            else if(carHeight > passengerCarMaxHeight) // определяем грузовой по высоте
            {
                // Double weight
                Double weight = WeightMeter.getWeight(car); // получаем массу
                //Грузовой автомобиль
                if(weight > passengerCarMaxWeight) // определяем грузовой ли
                {
                    price = passengerCarPrice; // получаем стоимость
                    if(car.hasVehicle()) { // есть ли прицеп?
                        price = price + vehicleAdditionalPrice; // если да, накидываем стоимость
                    }
                }
                //Легковой автомобиль
                else {
                    price = cargoCarPrice; // для легковушки даём обычную цену
                }
            }
            else {
                price = passengerCarPrice; // тут гдето ошибка.. но пока тоже задаём цену
            }

            /**
             * Проверка скорости подъезда и выставление штрафа
             */
            // Integer carSpeed
            Integer carSpeed = Camera.getCarSpeed(car); // снова получаем скорость, кстати странно,
                                                // вначале данные другие, т.к. снова задаём на рандоме
            if(carSpeed > criminalSpeed) // проверка на лимит скорости
            {
                Police.call("cкорость автомобиля - " + carSpeed + " км/ч, номер - " + car.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue; // если да, блокируем путь, зовём копов и смотрим след транспорт
            }
            else if(carSpeed > maxOncomingSpeed) // проверка на превышение скорости
            {
                // Integer overSpeed
                Integer overSpeed = carSpeed - maxOncomingSpeed; // определяем на сколько превысил
                // Integer totalFine
                Integer totalFine = finePerGrade * (1 + overSpeed / speedFineGrade); // задаём штраф
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine; // прибавляем штраф к итогу
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб."); // пишем итог
        }

    }

    /**
     * Открытие шлагбаума
     */
    public static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    public static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}