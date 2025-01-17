package controller;

import hospital.action.*;
import user.action.*;
import util.HttpMethod;

public class ActionFactory {
	private ActionFactory() {
	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String path, String command, HttpMethod method) {
		Action action = null;

		if (path == null || command == null)
			return action;

		if (path.equals("users"))
			return getUserAction(command, method);
		else if (path.equals("hospital"))
			return getHospitalAction(command, method);

		return action;
	}

	private Action getUserAction(String command, HttpMethod method) {
		Action action = null;

		if (command.equals("join") && method == HttpMethod.POST)
			return new JoinFormAction();
		if (command.equals("login") && method == HttpMethod.POST)
			return new LoginFormAction();
		if (command.equals("update") && method == HttpMethod.POST)
			return new JoinFormAction();
		if (command.equals("delete") && method == HttpMethod.POST)
			return new JoinFormAction();
		if (command.equals("logout") && method == HttpMethod.GET)
			return new JoinFormAction();

		return action;
	}

	private Action getHospitalAction(String command, HttpMethod method) {
		Action action = null;
		if (command.equals("symptom") && method == HttpMethod.POST)
			return new SymptomChcekAction();
		else if (command.equals("list") && method == HttpMethod.GET)
			return new HospitalsListAction();
		return action;
	}
}