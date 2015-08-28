package com.oct.ga.comm.cmd.inlinecast;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.RespCommand;
import com.oct.ga.comm.domain.msgflow.MsgFlowBasicInfo;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class InlinecastTaskLogResp
		extends RespCommand
{
	public InlinecastTaskLogResp()
	{
		this.setTag(Command.INLINECAST_TASK_LOG_RESP);
	}

	public InlinecastTaskLogResp(MsgFlowBasicInfo notify)
	{
		this();

		this.notify = notify;
	}

	@Override
	public InlinecastTaskLogResp decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 1;
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");
		TlvParser.decodeChildren(tlv, childCount);

		int i = 0;

		TlvObject tJson = tlv.getChild(i++);
		String json = new String(tJson.getValue(), "UTF-8");
		logger.debug("json: " + json);
		if (json != null) {
			Gson gson = new Gson();
			notify = gson.fromJson(json, MsgFlowBasicInfo.class);
		}

		return this;
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;

		Gson gson = new Gson();
		String json = gson.toJson(notify);
		TlvObject tJson = new TlvObject(i++, json);

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.push(tJson);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	private MsgFlowBasicInfo notify;

	public MsgFlowBasicInfo getNotify()
	{
		return notify;
	}

	public void setNotify(MsgFlowBasicInfo notify)
	{
		this.notify = notify;
	}

	private final static Logger logger = LoggerFactory.getLogger(InlinecastTaskLogResp.class);

}
