/**
 * Profile Model for Player. Player contains a profile
 */
package edu.utdallas.gamePlayEngine.model;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;

public class Profile extends Observable {

	private Date availability;
	private String communication;
	private List<String> degrees;
	private String demographics;
	private int experienceYears;
	private String leadership;
	private String resumePhotoLink;
	private List<String> skills;
	private String teamwork;
	private String title;
	//new code
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	
	public Date getAvailability() {
		return availability;
	}

	@XmlElement(name="communication")
	public String getCommunication() {
		return communication;
	}

	@XmlElement(name="degrees")
	public List<String> getDegrees() {
		return degrees;
	}

	@XmlElement(name="demographics")
	public String getDemographics() {
		return demographics;
	}

	@XmlElement(name="experience")
	public int getExperienceYears() {
		return experienceYears;
	}

	@XmlElement(name="leadership")
	public String getLeadership() {
		return leadership;
	}

	
	public String getResumePhotoLink() {
		return resumePhotoLink;
	}

	@XmlElement(name="skills")
	public List<String> getSkills() {
		return skills;
	}

	@XmlElement(name="teamwork")
	public String getTeamwork() {
		return teamwork;
	}

	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}

	public void setAvailability(Date availability) {
		this.availability = availability;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public void setDegrees(List<String> degrees) {
		this.degrees = degrees;
	}

	public void setDemographics(String demographics) {
		this.demographics = demographics;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public void setLeadership(String leadership) {
		this.leadership = leadership;
	}

	public void setResumePhotoLink(String resumePhotoLink) {
		this.resumePhotoLink = resumePhotoLink;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public void setTeamwork(String teamwork) {
		this.teamwork = teamwork;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
