package framesis.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import framesis.api.Analysis;
import framesis.api.TaskRegistry;
import framesis.api.TextMiningTask;
import framesis.weka.Tasks.StringToWordVectorTask;
import framesis.weka.Tasks.NaiveBayesTask;

public class Main {

	public static void main(String[] args)
	{
		System.out.println("Register Tasks");
		TaskRegistry.register(StringToWordVectorTask.class);
		TaskRegistry.register(NaiveBayesTask.class);
		
		System.out.println("Create Task Instances");
		List<TextMiningTask> tasks = TaskRegistry.createTasks();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("file", "/home/user/Frameworks/weka/data/ReutersCorn-train.arff");
		params.put("classAttribute", "class-att");
		
		Analysis analysis = new Analysis();
		
		Iterator<TextMiningTask> iter = tasks.iterator();
		while(iter.hasNext())
		{
			TextMiningTask task = iter.next();
			System.out.println(task.getDescription());
			analysis.setTextMiningTask(task);
			
			String output = analysis.execute(params);
			System.out.println("Created " + output);
			params.put("file", output);
		}
	}
}
