package edu.utdallas.gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

import edu.utdallas.gamePlayEngine.model.Audio;
import edu.utdallas.gamePlayEngine.model.Props;

public class DeclarationsInitializations {

	private Props props;
	private Audio audio;
	
	@XmlElement(name="Props")
	public Props getProps() {
		return props;
	}
	public void setProps(Props props) {
		this.props = props;
	}
	@XmlElement(name="Audio")
	public Audio getAudio() {
		return audio;
	}
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	
	
	
}
