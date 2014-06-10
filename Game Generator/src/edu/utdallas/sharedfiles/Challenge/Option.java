package edu.utdallas.sharedfiles.Challenge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Option")
public class Option 
{
	private String text;
	private Hint hint;

    @XmlElement(name = "Text")
	public String getText() 
	{
		return text;
	}
	public void setText(String text) 
	{
		this.text = text;
	}
    @XmlElement(name = "Hint")
	public Hint getHint() 
	{
		return hint;
	}
	public void setHint(Hint hint) 
	{
		this.hint = hint;
	}
}
