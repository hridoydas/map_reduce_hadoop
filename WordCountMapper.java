import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;


public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable oneIntegerWritable = new IntWritable(1);
    private Text word = new Text(); 

    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {
        StringTokenizer stringTokenizer = new StringTokenizer(inputValue.toString());
        while (stringTokenizer.hasMoreTokens()) {
            word.set(stringTokenizer.nextToken());
            context.write(word, oneIntegerWritable);
        }
    }
}
