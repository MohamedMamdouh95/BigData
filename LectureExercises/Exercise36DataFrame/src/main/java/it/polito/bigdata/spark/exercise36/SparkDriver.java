package it.polito.bigdata.spark.exercise36;

import org.apache.spark.sql.Dataset;
import static org.apache.spark.sql.functions.avg;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDriver {

	public static void main(String[] args) {

		String inputPath;

		inputPath = args[0];

		// Create a Spark Session object and set the name of the application
		SparkSession ss = SparkSession.builder().appName("Spark Exercise #36 - Dataset").getOrCreate();

		// Read the content of the input file and store it into a DataFrame
		// Meaning of the columns of the input file: sensorId,date,PM10 value
		// (μg/m3 )\n
		// The input file has no header. Hence, the name of the columns of
		// DataFrame will be _c0, _c1, _c2
		Dataset<Row> dfReadings = ss.read().format("csv").option("header", false).option("inferSchema", true)
				.load(inputPath);

		// Apply the average aggregate function over the values of the third column
		// of the dfReadings DataFrame
		Dataset<Row> avgValueDF = dfReadings.agg(avg("_c2"));

		// maxValueDF contains only one Row with a field called max(c_2).
		// Select it by using the first action
		Row rowAvgValue = avgValueDF.first();
		// Retrieve the value of the column "avg(_c2)" from the selected Row object
		Double avgValue= (Double) rowAvgValue.getAs("avg(_c2)");

		// Print the result on the standard output of the Driver
		// The name of the column is "avg(_c2)"
		System.out.println(avgValue);

		// Close the Spark context
		ss.stop();
	}
}
