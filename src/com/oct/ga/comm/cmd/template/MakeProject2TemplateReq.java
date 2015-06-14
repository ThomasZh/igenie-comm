package com.oct.ga.comm.cmd.template;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.Command;
import com.oct.ga.comm.cmd.ReqCommand;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class MakeProject2TemplateReq
		extends ReqCommand
{
	public MakeProject2TemplateReq()
	{
		super();

		this.setTag(Command.MAKE_PROJECT_TO_TEMPLATE_REQ);
	}

	@Override
	public MakeProject2TemplateReq decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		logger.info("from tlv:(tag=" + Command.MAKE_PROJECT_TO_TEMPLATE_REQ + ", child=2) to command");

		TlvParser.decodeChildren(tlv, 3);

		TlvObject tSequence = tlv.getChild(0);
		sequence = TlvByteUtil.byte2Int(tSequence.getValue());
		logger.debug("sequence: " + sequence);

		TlvObject tTaskId = tlv.getChild(1);
		taskId = new String(tTaskId.getValue(), "UTF-8");
		logger.debug("taskId: " + taskId);

		TlvObject tTemplateName = tlv.getChild(2);
		templateName = new String(tTemplateName.getValue(), "UTF-8");
		logger.debug("templateName: " + templateName);

		return this;
	}

	private String taskId;
	private String templateName;

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	private final static Logger logger = LoggerFactory.getLogger(MakeProject2TemplateReq.class);
}
