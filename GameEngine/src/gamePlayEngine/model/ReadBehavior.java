package gamePlayEngine.model;

public class ReadBehavior {

	private String behavior;
	private String model;
	private String action;
	private String parameter;

	/**
	 * Following behavior is similar to MATLAB Scripting and is in format
	 * Model.Action.Parameter
	 */

	public ReadBehavior(String behavior) {

		String behArray[] = behavior.split("\\.");

		if (behArray.length == 3) {
			this.model = behArray[0];
			this.action = behArray[1];
			this.parameter = behArray[2];
		}
		else if (behArray.length == 2) {
			this.model = behArray[0];
			this.action = behArray[1];
		}
		else if (behArray.length == 1) {
			this.model = behArray[0];
		}

	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
