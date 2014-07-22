package edu.utdallas.gamewizard;

import java.util.ArrayList;

import edu.utdallas.gamespecification.QuizChallenge;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SuggQuestion {
	@XmlElement(name="Challenge")
	ArrayList <QuizChallenge> questionList;
	
	public SuggQuestion(){
		questionList = new ArrayList <QuizChallenge>();
	}
	
	@XmlElement
	public void setQuesList(ArrayList <QuizChallenge> q){
		this.questionList = q;
	}
	
	public ArrayList <QuizChallenge> getQuesList(){
		return questionList;
	}
}
