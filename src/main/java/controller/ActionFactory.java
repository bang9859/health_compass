package controller;

import user.action.*;
import util.HttpMethod;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String path, String command, HttpMethod method) {
		Action action = null;
		
		if(path == null || command == null)
			return action;
		
		if(path.equals("users"))
			return getUserAction(command, method);
		
		return action;
	}
	
	private Action getUserAction(String command, HttpMethod method) {
		Action action = null;
		
		if(command.equals("join") && method == HttpMethod.POST)
			return new JoinFormAction();
		if(command.equals("login") && method == HttpMethod.POST)
			return new LoginFormAction();
		if(command.equals("update") && method == HttpMethod.POST)
			return new JoinFormAction();
		if(command.equals("delete") && method == HttpMethod.POST)
			return new JoinFormAction();
		if(command.equals("logout") && method == HttpMethod.GET)
			return new JoinFormAction();
		
		return action;
	}
}