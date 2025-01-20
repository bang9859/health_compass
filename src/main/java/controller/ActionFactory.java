package controller;

import hospital.action.*;
import schedule.action.AddScheduleAction;
import schedule.action.GetMedicineInfoAction;
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
		else if (path.equals("schedule"))
			return getScheduleAction(command, method);

		return action;
	}

	private Action getUserAction(String command, HttpMethod method) {
		Action action = null;

		if (command.equals("join") && method == HttpMethod.POST)
			return new JoinFormAction();
		else if (command.equals("login") && method == HttpMethod.POST)
			return new LoginFormAction();
		else if(command.equals("update") && method == HttpMethod.POST)
			return new UpdateFormAction();
		else if(command.equals("delete") && method == HttpMethod.POST)
			return new DeleteFormAction();
		else if(command.equals("logout") && method == HttpMethod.GET)
			return new LogoutAction();
		else if(command.equals("search-username") && method == HttpMethod.POST)
			return new SearchUsernameAction();
		else if (command.equals("search-email") && method == HttpMethod.POST)
			return new SearchEmailAction();
		else if (command.equals("search-tel") && method == HttpMethod.POST)
			return new SearchTelAction();

		return action;
	}

	private Action getHospitalAction(String command, HttpMethod method) {
		Action action = null;
		if (command.equals("search") && method == HttpMethod.POST)
			return new HospitalsSearchAction();
		else if (command.equals("list") && method == HttpMethod.GET)
			return new HospitalsListAction();

		return action;
	}

	public Action getScheduleAction(String command, HttpMethod method) {
		Action action = null;

		if (command.equals("add")&& method == HttpMethod.POST) {
			return new AddScheduleAction();
		}else if(command.equals("search-medicine") && method == HttpMethod.POST) {
		        return new GetMedicineInfoAction();
		    }

		
		return action;
	}
}