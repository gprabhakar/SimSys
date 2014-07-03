package edu.utdallas.gamewizard;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Test 
{
	public static void main(String args[])
	{
		try
		{
			File file = new File("Office, Classroom\\template.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(GameTemplate.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GameTemplate gametemplate = (GameTemplate) jaxbUnmarshaller.unmarshal(file);
			System.out.println(gametemplate);
			System.out.println(gametemplate.getActs().get(1).getScenes().get(1).getScreens().get(0).getType());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
