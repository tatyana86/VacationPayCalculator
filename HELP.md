## Test task for Neoflex
### Описание задания:
  
Приложение "Калькулятор отпускных".  
Микросервис на SpringBoot + Java 11 c одним API:  
GET "/calculate"  
  
Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.  
Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться расчет отпускных с учётом праздников и выходных.  
 

### API-запросы
- Простой запрос:  
http://localhost:8080/calculate?averageSalary=90000.00&vacationDays=28  
Результат:

  ![1](src/main/resources/static/1.png)

  
- Запрос с указанием точного дня ухода в отпуск:  
http://localhost:8080/calculate?averageSalary=90000.00&vacationDays=28&startVacationDate=2024-10-15  
Результат:

  ![2](src/main/resources/static/2.png) 


