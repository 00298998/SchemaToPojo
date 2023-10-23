import Pojo.SolverRequest;
import com.aa.opshub.avro.conversion.AvroMessageTransformerImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.io.File;

public class PojoToAvroConverter
{
	public static void main(String[] args) throws Exception
	{
		// convert pojo to avro
		// Schema schema = ReflectData.get().getSchema(SolverRequest.class);
		// System.out.println(schema.toString(true));
		//
		// File schemaFile = new File("src/main/resources/SolverRequest.avsc");
		// FileWriter filewriter = new FileWriter(schemaFile);
		// filewriter.append(schema.toString(true));
		// filewriter.close();


		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		AvroMessageTransformerImpl avroMessageTransformer = new AvroMessageTransformerImpl();
		Schema schema = new Schema.Parser().parse(new File("src/main/resources/SolverRequest.avsc"));
		byte[] avroBytes = schema.toString().getBytes();

		// avroToObject
		GenericRecord genericRecord = avroMessageTransformer.avroToObject(avroBytes);
		System.out.println("record >>> " + genericRecord.toString());
		SolverRequest solverRequest = mapper.readValue(genericRecord.toString(), SolverRequest.class);
		System.out.println(solverRequest);

		// avroToUserObject
		SolverRequest solverRequestObject = (SolverRequest) avroMessageTransformer.avroToUserObject(avroBytes, SolverRequest.class);
		System.out.println("Object " + solverRequestObject);
		System.out.println("Object " + solverRequestObject.getPosition());
		System.out.println("Object " + solverRequestObject.getAirlineCode());
	}
}
