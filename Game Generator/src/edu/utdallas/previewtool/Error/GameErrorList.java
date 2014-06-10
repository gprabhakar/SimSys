package edu.utdallas.previewtool.Error;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.previewtool.Error.PreviewError.Severity;

public class GameErrorList extends ArrayList<PreviewError>
{	
	public boolean hasCriticalErrors() 
	{
		for(PreviewError e : this)
			if(e.getSeverity() == Severity.HIGH)
				return true;
		return false;
	}
}
