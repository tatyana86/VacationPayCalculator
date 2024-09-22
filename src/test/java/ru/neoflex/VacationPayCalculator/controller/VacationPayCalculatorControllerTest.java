package ru.neoflex.VacationPayCalculator.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.neoflex.VacationPayCalculator.service.DaysCalculationService;
import ru.neoflex.VacationPayCalculator.service.VacationPayCalculatorService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationPayCalculatorControllerTest {
    private VacationPayCalculatorService vacationPayCalculatorService;
    private DaysCalculationService daysCalculationService;

    private final BigDecimal testAverageSalaryPerYear = new BigDecimal("90000.00");
    private final int testVacationDays = 28;
    private final LocalDate testStartVacationDate = LocalDate.of(2024, 10, 15);
    
    @BeforeEach
    void init() {
        vacationPayCalculatorService = new VacationPayCalculatorService();
        daysCalculationService = new DaysCalculationService();
    }
    
    
    @Test
    void calculationOfVacationPayForEmployeeUsingSimpleQueryTest() {

        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testVacationDays).getVacationPay();
        assertEquals(BigDecimal.valueOf(74825.76).stripTrailingZeros(), actual.stripTrailingZeros());
    }

    @Test
    void calculationOfVacationPayForEmployeeUsingQueryWithDateTest() {

        int testPaidVacationDays = daysCalculationService.calculatePaidDays(testVacationDays, testStartVacationDate);
        BigDecimal actual = vacationPayCalculatorService.getVacationPayCalculation(testAverageSalaryPerYear, testPaidVacationDays).getVacationPay();
        assertEquals(BigDecimal.valueOf(50774.73).stripTrailingZeros(), actual.stripTrailingZeros());
    }

}
