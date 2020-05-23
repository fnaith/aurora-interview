package com.example.AuroraInterview.service;

import io.swagger.Swagger2SpringBoot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Swagger2SpringBoot.class)
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void givenMenu_whenSave_thenGetOk() {
        String name = String.valueOf((new Random()).nextInt());

        io.swagger.model.Menu body1 = buildDummyBody();
        body1.setName(name);
        List<io.swagger.model.Menu> bodies = menuService.createMenu(Arrays.asList(body1)).getBody();

        String id = bodies.get(0).getId().toString();
        io.swagger.model.Menu body2 = menuService.readMenu(id).getBody();
        assertEquals(name, body2.getName());
    }

    @Test
    public void givenMenus_whenSave_thenGetAllOk() {
        List<io.swagger.model.Menu> bodies1 = menuService.listMenu().getBody();

        String name = String.valueOf((new Random()).nextInt());

        io.swagger.model.Menu body = buildDummyBody();
        body.setName(name);
        List<io.swagger.model.Menu> bodies2 = menuService.createMenu(IntStream.range(0, (new Random()).nextInt() % 20)
                .mapToObj(i -> body).collect(Collectors.toList())).getBody();

        List<io.swagger.model.Menu> bodies3 = menuService.listMenu().getBody();
        assertEquals(bodies3.size(), bodies1.size() + bodies2.size());
    }

    @Test
    public void givenMenu_whenSave_thenUpdateOk() {
        String name = String.valueOf((new Random()).nextInt());
        String newName = String.valueOf((new Random()).nextInt());

        io.swagger.model.Menu body1 = buildDummyBody();
        body1.setName(name);
        List<io.swagger.model.Menu> bodies = menuService.createMenu(Arrays.asList(body1)).getBody();

        String id = bodies.get(0).getId().toString();
        io.swagger.model.Menu body2 = menuService.readMenu(id).getBody();
        assertEquals(name, body2.getName());

        body1.setName(newName);
        io.swagger.model.Menu body3 = menuService.updateMenu(id, body1).getBody();
        assertEquals(newName, body3.getName());
    }

    @Test
    public void givenMenu_whenSave_thenDeleteOk() {
        String name = String.valueOf((new Random()).nextInt());

        io.swagger.model.Menu body1 = buildDummyBody();
        body1.setName(name);
        List<io.swagger.model.Menu> bodies = menuService.createMenu(Arrays.asList(body1)).getBody();

        String id = bodies.get(0).getId().toString();
        io.swagger.model.Menu body2 = menuService.readMenu(id).getBody();
        assertEquals(name, body2.getName());

        menuService.deleteMenu(id);
        assertEquals(HttpStatus.NOT_FOUND, menuService.readMenu(id).getStatusCode());
    }

    private io.swagger.model.Menu buildDummyBody() {
        io.swagger.model.Menu body = new io.swagger.model.Menu();

        body.setId(-1);
        body.setName("");
        body.setContent("");
        body.setCreatedBy("");
        body.setCreatedAt(new Date().getTime());
        body.setUpdatedBy("");
        body.setUpdatedAt(new Date().getTime());

        return body;
    }
}
