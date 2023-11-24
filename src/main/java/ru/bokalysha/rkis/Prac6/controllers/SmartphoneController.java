package ru.bokalysha.rkis.Prac6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bokalysha.rkis.Prac6.models.Smartphone;
import ru.bokalysha.rkis.Prac6.services.SmartphoneService;

/**
 * Контроллер для работы со смартофном.
 */
@Controller
@RequestMapping("/smartphone")
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    @Autowired
    public SmartphoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    /**
     * Обрабатывает запрос на получение списка смартфонов.
     *
     * @param price Цена для фильтрации.
     * @param model Объект модели.
     * @return Представление списка смартфонов.
     */
    @GetMapping()
    public String index(@RequestParam(name = "price", required = false) Float price, Model model) {
        if (price != null) {
            model.addAttribute("smartphone", smartphoneService.filterByPrice(price));
        } else {
            model.addAttribute("smartphone", smartphoneService.findAll());
        }
        return "smartphone/main";
    }

    /**
     * Обрабатывает запрос на редактирование информации о смартфонах.
     *
     * @param id Идентификатор смартфона.
     * @param model Объект смартфона.
     * @return Представление для редактирования.
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("smartphone", smartphoneService.findOne(id));
        return "smartphone/edit";
    }

    /**
     * Обрабатывает запрос на добавление нового смартфона.
     *
     * @param smartphone Объект смартфона для добавления.
     * @return Представление для добавления смартфона.
     */
    @GetMapping("/add")
    public String addFurniture(@ModelAttribute("smartphone") Smartphone smartphone) {
        return "smartphone/add";
    }

    /**
     * Обрабатывает запрос на создание нового смартфона.
     *
     * @param smartphone Объект смартфона для создания.
     * @param bindingResult Результаты валидации.
     * @return Представление для добавления смартфонов или перенаправление на список смартфонов.
     */
    @PostMapping()
    public String create(
            @ModelAttribute("smartphone") @Valid Smartphone smartphone,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "smartphone/add";
        }

        smartphoneService.save(smartphone);
        return "redirect:/smartphone";
    }

    /**
     * Обрабатывает запрос на обновление информации о смартфонов.
     *
     * @param smartphone Объект смартфона для обновления.
     * @param bindingResult Результаты валидации.
     * @param id Идентификатор смартфона.
     * @return Представление для редактирования или перенаправление на список смартфонов.
     */
    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("smartphone") @Valid Smartphone smartphone,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            return "smartphone/edit";
        }
        smartphoneService.update(id, smartphone);
        return "redirect:/smartphone";
    }

    /**
     * Обрабатывает запрос на удаление смартфона.
     *
     * @param id Идентификатор смартфона.
     * @return Перенаправление на список смартфонов.
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        smartphoneService.delete(id);
        return "redirect:/smartphone";
    }
}
