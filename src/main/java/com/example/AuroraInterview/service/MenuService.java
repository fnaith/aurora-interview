package com.example.AuroraInterview.service;

import com.example.AuroraInterview.model.Menu;
import com.example.AuroraInterview.repository.MenuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MenuService {

    private static boolean isActive(Menu menu) {
        return ((null != menu) && !menu.getDeleted());
    }

    private static io.swagger.model.Menu buildBody(Menu menu) {
        io.swagger.model.Menu body = new io.swagger.model.Menu();

        body.setId(menu.getId());
        body.setName(menu.getName());
        body.setContent(menu.getContent());
        body.setCreatedBy(menu.getCreatedBy());
        body.setCreatedAt(menu.getCreatedAt());
        body.setUpdatedBy(menu.getUpdatedBy());
        body.setUpdatedAt(menu.getUpdatedAt());

        return body;
    }

    Logger logger = LogManager.getLogger(getClass());

    @Resource
    private MenuRepository menuRepository;

    public ResponseEntity<List<io.swagger.model.Menu>> createMenu(List<io.swagger.model.Menu> bodies) {
        try {
            long now = new Date().getTime();
            int nextId = (int) menuRepository.count();

            List<Menu> menus = IntStream.range(0, bodies.size())
                    .mapToObj(index -> {
                        io.swagger.model.Menu body = bodies.get(index);
                        body.setUpdatedBy(body.getCreatedBy());

                        Menu menu = new Menu();

                        menu.setId(nextId + index);
                        menu.setName(body.getName());
                        menu.setContent(body.getContent());
                        menu.setCreatedBy(body.getCreatedBy());
                        menu.setCreatedAt(now);
                        menu.setUpdatedBy(body.getUpdatedBy());
                        menu.setUpdatedAt(now);
                        menu.setDeleted(false);

                        return menu;
                    }).collect(Collectors.toList());

            menus = menuRepository.saveAll(menus);

            for (int i = 0; i < menus.size(); ++i) {
                Menu menu = menus.get(i);
                io.swagger.model.Menu body = bodies.get(i);
                body.setId(menu.getId());
                body.setCreatedAt(now);
                body.setUpdatedAt(now);
            }

            return new ResponseEntity<>(bodies, HttpStatus.OK);
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<io.swagger.model.Menu> readMenu(String id) {
        Integer menuId;
        try {
            menuId = Integer.valueOf(id);
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Menu menu = menuRepository.findById(menuId).orElse(null);
            if (isActive(menu)) {
                return new ResponseEntity<>(buildBody(menu), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<io.swagger.model.Menu> updateMenu(String id, io.swagger.model.Menu body) {
        Integer menuId;
        try {
            menuId = Integer.valueOf(id);
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Menu menu = menuRepository.findById(menuId).orElse(null);
            if (isActive(menu)) {
                menu.setName(body.getName());
                menu.setContent(body.getContent());
                menu.setUpdatedBy(body.getUpdatedBy());

                long now = new Date().getTime();
                menu.setUpdatedAt(now);

                try {
                    menuRepository.save(menu);
                    body.setId(menuId);
                    body.setUpdatedAt(now);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                } catch (Throwable throwable) {
                    logger.error(throwable);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteMenu(String id) {
        Integer menuId;
        try {
            menuId = Integer.valueOf(id);
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Menu menu = menuRepository.findById(menuId).orElse(null);
            if (isActive(menu)) {
                menu.setDeleted(true);

                try {
                    menuRepository.save(menu);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Throwable throwable) {
                    logger.error(throwable);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<io.swagger.model.Menu>> listMenu(String userName) {
        try {
            return new ResponseEntity<>(menuRepository.findAll().stream()
                    .filter(menu -> isActive(menu) && userName.equals(menu.getCreatedBy()))
                    .map(menu -> buildBody(menu)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Throwable throwable) {
            logger.error(throwable);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
