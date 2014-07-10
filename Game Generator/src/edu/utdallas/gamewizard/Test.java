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
			File file = new File("Office, Classroom\\Enchanted Forest_Chars.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(CharList.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CharList charList = (CharList) jaxbUnmarshaller.unmarshal(file);
			System.out.println(charList);
			System.out.println(charList.getCharacters().get(0).getFileName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
