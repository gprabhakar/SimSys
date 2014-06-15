package gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

public class Identifier extends Graphic{

	/**
	 * This file contains Identifier implementation which extends Graphic
	 */

		private String id;
		
		@XmlElement(name="id")
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	
	
}
