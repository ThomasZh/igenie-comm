package com.oct.ga.comm.cmd.club;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class ActivityQueryMemberReq
		extends ReqCommand
{
	public ActivityQueryMemberReq()
	{
		super();

		this.setTag(Command.ACTIVITY_QUERY_MEMBER_REQ);
	}

	@Override
	public ActivityQueryMemberReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		logger.info("from tlv:(tag=" + Command.ACTIVITY_QUERY_MEMBER_REQ + ", child=2) to command");

		TlvParser.decodeChildren(tlv, 2);

		TlvObject tSequence = tlv.getChild(0);
		sequence = TlvByteUtil.byte2Int(tSequence.getValue());
		logger.debug("sequence: " + sequence);

		TlvObject tActivityId = tlv.getChild(1);
		activityId = new String(tActivityId.getValue(), "UTF-8");
		logger.debug("activityId: " + activityId);

		return this;
	}

	private String activityId;

	public String getActivityId()
	{
		return activityId;
	}

	public void setActivityId(String activityId)
	{
		this.activityId = activityId;
	}

	private final static Logger logger = LoggerFactory.getLogger(ActivityQueryMemberReq.class);

}
