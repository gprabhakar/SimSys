/**
 * RewardBoundary class used as a boundary for Reward Model
 */
package gamePlayEngine.model;

public class RewardBoundary {
	private RewardControl rewardControl;
	
	public RewardBoundary(Reward reward) {
		rewardControl = new RewardControl(reward);
	}

	public void adddPoints(int points) {
		rewardControl.addPoints(points);
		
	}
}
