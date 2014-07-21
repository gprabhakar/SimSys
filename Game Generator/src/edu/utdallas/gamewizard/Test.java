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
			File file = new File("Office, Classroom\\QuestionRepo/Interpret a multiplication equation as a comparison, e.g., interpret 35 = 5 × 7 as a statement that 35 is 5 times as many as 7 and 7 times as many as 5. Represent verbal statements of multiplicative comparisons as multiplication equations_Dialog.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(SuggQuestion.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SuggQuestion sugg = (SuggQuestion) jaxbUnmarshaller.unmarshal(file);
			System.out.println(sugg);
			//System.out.println(sugg.getQuesList().get(0).getOption().get(0).getAssessment());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
