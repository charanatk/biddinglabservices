package com.notify.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notify.app.model.MenuBean;
import com.notify.app.repo.MenuRep;

@RestController()
public class MenuRestController {

	@Autowired
	private MenuRep menuRep;

	@GetMapping(value = "/menu/items/getMenuItems")
	public List<MenuBean> getMenuItems() {
		return menuRep.findByUsername(getLoggedInUserName());
	}

	@PostMapping(value = "/menu/items/saveMenu")
	public MenuBean saveMenu(@RequestBody MenuBean menuBean) {
		return menuRep.save(menuBean);
	}

	@DeleteMapping(value = "/menu/items/deleteMenu")
	public void deleteMenu(@RequestBody MenuBean menuBean) {
		menuRep.delete(menuBean);
	}

	public final String getLoggedInUserName() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final String username = user.getUsername();
		return username;

	}

}
