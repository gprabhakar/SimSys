/**
 * RewarControl class used to define process for Reward Model
 */
package edu.utdallas.gamePlayEngine.model;

public class RewardControl {

	private Reward reward;
	public RewardControl(Reward reward) {
		this.reward = reward;
	}
	public void addPoints(int points) {
		reward.addPoints(points);		
	}
}
