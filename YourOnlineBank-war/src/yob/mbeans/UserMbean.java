package yob.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import yob.controller.UserContainer;
import yob.repository.UserRepository;
import yob.repository.entities.User;
import yob.util.FacesContextUtils;

@Named("userMbean")
@RequestScoped
public class UserMbean {
	@EJB
	UserRepository userRepository;
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public List<User> getAllAviliableUser(){
		List<User> aviliableUsers = new ArrayList<User>();
		for(User user:this.getAllUser()) {
			if (user.getAccount() == null ||
					user.getAccount().getAccountNo().equals("")) {
				aviliableUsers.add(user);
			}
		}
		return aviliableUsers;
	}
	public User getUserByUuid(String uuid){
		return userRepository.findByUUID(uuid);
	}
	public List<User> getUserByType(String type){
		return userRepository.findByType(type);
	}
	public void remove(String uuid) {
		userRepository.del(uuid);
	}
	public void edit(User user) {
		try {
			userRepository.update(user);
			FacesContextUtils.showMessage("Update successfully...");
		} catch (Exception e) {
			FacesContextUtils.showMessage("Update failed...");
			e.printStackTrace();
		}
	}
	public void add(User user) {
		userRepository.add(user);
	}
	public void add(UserContainer userContainer) {
		
				User user = convertToUser(userContainer);
				this.add(user);
			
		
	}
	public User convertToUser(UserContainer userContainer) {
		return new User(userContainer.getUsername(),
				userContainer.getPassword(), userContainer.getType());
	}
}