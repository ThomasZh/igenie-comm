package com.oct.ga.comm.cmd.inlinecast;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.domain.msg.NotifyGomoku;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class InlinecastGomokuNotifyReq
		extends ReqCommand
{
	public InlinecastGomokuNotifyReq()
	{
		super();

		this.setTag(Command.INLINECAST_GOMOKU_NOTIFY_REQ);
	}

	public InlinecastGomokuNotifyReq(NotifyGomoku nofity)
	{
		this();

		this.setNofity(nofity);
	}

	@Override
	public InlinecastGomokuNotifyReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		logger.info("from tlv:(tag=" + Command.INLINECAST_GOMOKU_NOTIFY_REQ + ", child=1) to command");

		TlvParser.decodeChildren(tlv, 1);

		TlvObject tJson = tlv.getChild(0);
		String json = new String(tJson.getValue(), "UTF-8");
		logger.debug("json: " + json);

		setNofity(new NotifyGomoku().decode(json));

		return this;
	}

	public NotifyGomoku getNofity()
	{
		return nofity;
	}

	public void setNofity(NotifyGomoku nofity)
	{
		this.nofity = nofity;
	}

	private NotifyGomoku nofity;

	private final static Logger logger = LoggerFactory.getLogger(InlinecastGomokuNotifyReq.class);

}
