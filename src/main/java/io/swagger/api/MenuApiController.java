package io.swagger.api;

import com.example.AuroraInterview.service.MenuService;
import io.swagger.model.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:46:06.832+08:00")

@Controller
public class MenuApiController implements MenuApi {

    @Autowired
    private MenuService menuService;

    @Override
    public ResponseEntity<List<Menu>> createMenu(List<Menu> bodies) {
        String userName = getUserName();
        bodies.stream().forEach(body -> body.setCreatedBy(userName));
        return menuService.createMenu(bodies);
    }

    @Override
    public ResponseEntity<Menu> readMenu(String id) {
        return menuService.readMenu(id);
    }

    @Override
    public ResponseEntity<Menu> updateMenu(String id, Menu body) {
        body.setUpdatedBy(getUserName());
        return menuService.updateMenu(id, body);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(String id) {
        return menuService.deleteMenu(id);
    }

    @Override
    public ResponseEntity<List<Menu>> listMenu() {
        return menuService.listMenu(getUserName());
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
