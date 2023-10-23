package Pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class SolverRequest implements Serializable
{
	private String airlineCode;
	private String containerLink;
	private String createdBy;
	private String env;
	private String eventSrcTimeStamp;
	private String filename;
	private String fleet;
	private String position;
	private String snapshotID;
	private String source;
	private String status;
	private int fileSize;
}