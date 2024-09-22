package ru.neoflex.VacationPayCalculator.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.neoflex.VacationPayCalculator.model.VacationPayResponse;
import ru.neoflex.VacationPayCalculator.service.DaysCalculationService;
import ru.neoflex.VacationPayCalculator.service.VacationPayCalculatorService;


@Tag(name = "Vacation pay calculator", 
	description = "Operations related to vacation pay calculations")
@RestController
public class VacationPayCalculatorController {
	
    private final VacationPayCalculatorService vacationPayCalculatorService;
    private final DaysCalculationService daysCalculationService;

    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService,
                                           DaysCalculationService daysCalculationService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
        this.daysCalculationService = daysCalculationService;
    }

    @GetMapping("/calculate")
    public VacationPayResponse getVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate
    ) {
        if (startVacationDate.isPresent()) {
            vacationDays = daysCalculationService.calculatePaidDays(vacationDays, startVacationDate.get());
        }
        return vacationPayCalculatorService.getVacationPayCalculation(averageSalaryPerYear, vacationDays);
    }

}
