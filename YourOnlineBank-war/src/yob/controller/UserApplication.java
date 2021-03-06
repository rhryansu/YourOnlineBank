package yob.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import yob.mbeans.UserMbean;
import yob.repository.entities.User;
import yob.util.FacesContextUtils;

@Named("userApp")
@ApplicationScoped
public class UserApplication implements Serializable {
	private List<User> users;
	private UserMbean userMbean;
	public UserApplication() {
		users = new ArrayList<User>();
		userMbean = FacesContextUtils.getManagedBean("userMbean");
		updateUsers();
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public UserMbean getUserMbean() {
		return userMbean;
	}
	public void setUserMbean(UserMbean userMbean) {
		this.userMbean = userMbean;
	}
	public void updateUsers() {
		users.clear();
		List<User> allUser = userMbean.getAllUser();
		for (User u : allUser) {
			users.add(u);
		}
	}
	public void remove(String uuid) {
		userMbean.remove(uuid);
		FacesContextUtils.showMessage("User("+uuid+") has been removed!");
		updateUsers();
	}
	public void add(UserContainer userContainer) {
		try {
			userMbean.add(userContainer);
			FacesContextUtils.showMessage("User has been added!");
		} catch (Exception e) {
			FacesContextUtils.showMessage("Adding Failed!");
			e.printStackTrace();
		}
		updateUsers();
	}
	public void getByWorker(){
		this.users = userMbean.getUserByType("woker");
	}
	public void getByUser() {
		this.users = userMbean.getUserByType("user");
	}
}