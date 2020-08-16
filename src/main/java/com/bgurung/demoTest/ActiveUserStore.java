package com.bgurung.demoTest;

import java.util.ArrayList;
import java.util.List;

public class ActiveUserStore {
	 
    public List<String> users;
 
    
	public ActiveUserStore() {
        users = new ArrayList<String>();
    }
 
    // standard getter and setter
	
	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

}