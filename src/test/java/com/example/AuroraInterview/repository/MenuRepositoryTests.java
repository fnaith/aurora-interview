package com.example.AuroraInterview.repository;

import com.example.AuroraInterview.model.Menu;
import io.swagger.Swagger2SpringBoot;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = Swagger2SpringBoot.class)
public class MenuRepositoryTests {

    @Resource
    private MenuRepository menuRepository;

    @Test
    public void givenMenu_whenSave_thenGetOk() {
        int id = (new Random()).nextInt();
        String name = String.valueOf((new Random()).nextInt());

        Menu menu1 = buildDummyMenu();
        menu1.setId(id);
        menu1.setName(name);
        menuRepository.save(menu1);

        Menu menu2 = menuRepository.findById(id).orElse(null);
        assertEquals(name, menu2.getName());
    }

    @Test
    public void givenMenu_whenSave_thenUpdateOk() {
        int id = (new Random()).nextInt();
        String name = String.valueOf((new Random()).nextInt());
        String newName = String.valueOf((new Random()).nextInt());

        Menu menu1 = buildDummyMenu();
        menu1.setId(id);
        menu1.setName(name);
        menuRepository.save(menu1);

        Menu menu2 = menuRepository.findById(id).orElse(null);
        assertEquals(name, menu2.getName());
        menuRepository.save(menu1);

        menu1.setName(newName);
        menuRepository.save(menu1);

        Menu menu3 = menuRepository.findById(id).orElse(null);
        assertEquals(newName, menu3.getName());
    }

    @Test
    public void givenMenu_whenSave_thenDeleteOk() {
        int id = (new Random()).nextInt();
        String name = String.valueOf((new Random()).nextInt());
        String newName = String.valueOf((new Random()).nextInt());

        Menu menu1 = buildDummyMenu();
        menu1.setId(id);
        menu1.setName(name);
        menuRepository.save(menu1);

        Menu menu2 = menuRepository.findById(id).orElse(null);
        assertEquals(name, menu2.getName());

        menu1.setName(newName);
        menuRepository.deleteById(id);

        Menu menu3 = menuRepository.findById(id).orElse(null);
        assertNull(menu3);
    }

    private Menu buildDummyMenu() {
        Menu menu = new Menu();

        menu.setId(-1);
        menu.setName("");
        menu.setContent("");
        menu.setCreatedBy("");
        menu.setCreatedAt(new Date().getTime());
        menu.setUpdatedBy("");
        menu.setUpdatedAt(new Date().getTime());
        menu.setDeleted(false);

        return menu;
    }
}
