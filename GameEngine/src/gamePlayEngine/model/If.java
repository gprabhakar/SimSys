package gamePlayEngine.model;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;

public class If extends Observable{
	private String evaluation;
	private String text;
	private String rewardPointsToAdd;
	private String rewardTitle;
	
	@XmlElement(name = "evaluation")
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	@XmlElement(name = "text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@XmlElement(name = "rewardPointsToAdd")
	public String getRewardPointsToAdd() {
		return rewardPointsToAdd;
	}
	public void setRewardPointsToAdd(String rewardPointsToAdd) {
		this.rewardPointsToAdd = rewardPointsToAdd;
	}
	
	@XmlElement(name = "rewardTitle")
	public String getRewardTitle() {
		return rewardTitle;
	}
	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}
	
}
