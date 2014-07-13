package edu.utdallas.gamespecification;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Profile", propOrder = {
	"profilePhoto",
    "title",
    "yearsOfExperience",
    "skills",
    "demographics",
    "degrees"
})
public class Profile
{
    @XmlElement(name = "ProfilePhoto", required = true)
	private String profilePhoto; 
    @XmlElement(name = "Title", required = true)
	private String title; 
    @XmlElement(name = "YearsOfExperience", required = true)
	private int yearsOfExperience; 
	//private String communication;
	//private String availability;
    @XmlElement(name = "Skills", required = true)
	private List<String> skills; 
    @XmlElement(name = "Demographics", required = true)
	private List<String> demographics;  
    @XmlElement(name = "Degrees", required = true)
	private List<String> degrees; 
	//private String teamwork;
	//private String attendance;

    
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	/*
    @XmlElement(name = "Communication")
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	} */
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	public List<String> getDemographics() {
		return demographics;
	}
	public void setDemographics(List<String> demographics) {
		this.demographics = demographics;
	}
	
	public List<String> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<String> degrees) {
		this.degrees = degrees;
	}

}
