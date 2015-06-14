package com.oct.ga.comm.cmd.following;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.RespCommand;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;

public class UnfollowingResp
		extends RespCommand
{
	public UnfollowingResp()
	{
		this.setTag(Command.UNFOLLOW_RESP);
	}

	public UnfollowingResp(short respState)
	{
		this();

		this.setRespState(respState);
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		TlvObject tResultFlag = new TlvObject(1, 2, TlvByteUtil.short2Byte(this.getRespState()));

		// 6 + 2
		int pkgLen = 8;
		logger.info("from command to tlv package:(tag=" + Command.UNFOLLOW_RESP + ", child=1, length=" + pkgLen
				+ ")");
		TlvObject tlv = new TlvObject(Command.UNFOLLOW_RESP, pkgLen);
		tlv.add(tResultFlag);
		return tlv;
	}

	private final static Logger logger = LoggerFactory.getLogger(UnfollowingResp.class);

}
