package it.polito.bigdata.hadoop.lab2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Lab  - Mapper
 */

/* Set the proper data types for the (key,value) pairs */
class MapperBigData extends Mapper<
                    Text, // Input key type
                    Text,         // Input value type
                    Text,         // Output key type
                    IntWritable> {// Output value type
	
	String prefix;

	protected void setup(Context context) {
		prefix = context.getConfiguration().get("beginningString").toString();
	}

	
	
    protected void map(
            Text key,   // Input key type
            Text value,         // Input value type
            Context context) throws IOException, InterruptedException {
    	
    	if(key.toString().startsWith(prefix)) {
    		context.write(new Text(key), new IntWritable(Integer.parseInt(value.toString())));
    	}
    	
    }
}
