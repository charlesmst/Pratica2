package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Menu;
import org.springframework.stereotype.Repository;

@Repository
public class MenuService extends Service<Menu>{

    public MenuService() {
        classRef = Menu.class;
    }
}
