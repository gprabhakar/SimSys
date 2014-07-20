package edu.utdallas.old.testfiles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import edu.utdallas.gamespecification.Challenge;
import edu.utdallas.gamespecification.Hint;
import edu.utdallas.gamespecification.Item;
import edu.utdallas.gamespecification.MultipleChoiceItem;
import edu.utdallas.gamespecification.Option;
import edu.utdallas.gamespecification.QuizChallenge;
import edu.utdallas.gamespecification.Stem;
import edu.utdallas.gamespecification.StemQuestion;
import edu.utdallas.gamespecification.StemText;

public class MainTestChallenge 
{
	public static void main(String[] args) 
	{
		QuizChallenge challenge = new QuizChallenge();
		ArrayList<Item> items = new ArrayList<Item>();
		MultipleChoiceItem item = new MultipleChoiceItem();
		ArrayList<Option> options = new ArrayList<Option>();
		Stem stem = new Stem();
		StemText st = new StemText();
		Hint stHint = new Hint();
		Hint stemQHint = new Hint();
		Hint optionHint = new Hint();
		stHint.setHint("this explains whatever");
		st.setHint(stHint);
		stem.setStemText(st);
		StemQuestion sq = new StemQuestion();
		stemQHint.setHint("answer this question");
		sq.setHint(stemQHint);
		stem.setStemQuestion(sq);
		item.setStem(stem);
		Option option = new Option();
		optionHint.setHint("an option");
		option.setHint(optionHint);
		for(int i = 0; i < 4; i++)
		{
			options.add(option);
		}
		item.setItemname("ItemTest");
		items.add(item);
		//challenge.setItem(items);
		
		try {
			 
			File file = new File("OutputChallenge.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Challenge.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			//jaxbMarshaller.marshal(challenge, file);
			jaxbMarshaller.marshal(challenge, System.out);
	 
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
		
		/*try
		{
			File file = new File("TestChallenge.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Challenge.class);
	 
			javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Challenge challenge1 = (Challenge) jaxbUnmarshaller.unmarshal(file);
			System.out.println(challenge1);
	 
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		  }*/

	}

}
