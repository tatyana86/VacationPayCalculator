package ru.neoflex.VacationPayCalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import ru.neoflex.VacationPayCalculator.model.VacationPayResponse;

@Service
public class VacationPayCalculatorService {

    private static final double AVERAGE_NUMBER_DAYS_IN_MONTH = 29.3; // среднее количество дней в месяце без учета федеральных праздников
    private static final double NDFL_PERCENT = 0.13; // процент НДФЛ

    /**
     Функция для расчёта отпускных сотрудника в рублях
     @param averageSalaryPerYear - средняя зарплата в рублях за 12 месяцев
     @param vacationDays         - количество дней отпуска
     @return возвращает сумму отпускных в рублях
     */

    public VacationPayResponse getVacationPayCalculation(BigDecimal averageSalaryPerYear,
                                                         int vacationDays) {
    	// подсчет среднего дневного заработка
        BigDecimal averageEarningsPerDay = averageSalaryPerYear.divide(BigDecimal.valueOf(AVERAGE_NUMBER_DAYS_IN_MONTH), 2, RoundingMode.HALF_EVEN);

    	// подсчет отпускных без вычета НДФЛ
        BigDecimal totalPayWithoutNDFL = averageEarningsPerDay.multiply(BigDecimal.valueOf(vacationDays));

    	// подсчет суммы НДФЛ
        BigDecimal amountNDFL = totalPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL_PERCENT)).setScale(0, RoundingMode.HALF_UP);

        // подсчет отпусных с учетом НДФЛ
        BigDecimal totalPay = totalPayWithoutNDFL.subtract(amountNDFL);

        return new VacationPayResponse("Сумма отпускных в рублях (с вычетом НДФЛ)", totalPay);
    }
	
}
