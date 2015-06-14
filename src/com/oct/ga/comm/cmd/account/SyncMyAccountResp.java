package com.oct.ga.comm.cmd.account;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oct.ga.comm.cmd.RespCommand;
import com.oct.ga.comm.domain.account.AccountDetailInfo;
import com.oct.ga.comm.tlv.TlvByteUtil;
import com.oct.ga.comm.tlv.TlvObject;
import com.oct.ga.comm.tlv.TlvParser;

public class SyncMyAccountResp
		extends RespCommand
{
	public SyncMyAccountResp()
	{
		this.setTag(SYNC_MY_ACCOUNT_RESP);
	}

	public SyncMyAccountResp(AccountDetailInfo account, int timestamp)
	{
		this();

		this.setAccount(account);
		this.setCurrentTimestamp(timestamp);
	}

	@Override
	public TlvObject encode()
			throws UnsupportedEncodingException
	{
		int i = 0;

		TlvObject accountName = new TlvObject(i++, account.getName());
		TlvObject phone = new TlvObject(i++, account.getTelephone());
		TlvObject decription = new TlvObject(i++, account.getDesc());
		TlvObject photo = new TlvObject(i++, account.getImageUrl());
		TlvObject tTimestamp = new TlvObject(i++, 4, TlvByteUtil.int2Byte(currentTimestamp));

		TlvObject tlv = new TlvObject(this.getTag());
		tlv.push(accountName);
		tlv.push(phone);
		tlv.push(decription);
		tlv.push(photo);
		tlv.push(tTimestamp);

		logger.debug("from command to tlv package:(tag=" + this.getTag() + ", child=" + i + ", length="
				+ tlv.getLength() + ")");

		return tlv;
	}

	@Override
	public SyncMyAccountResp decode(TlvObject tlv)
			throws UnsupportedEncodingException
	{
		this.setTag(tlv.getTag());

		int childCount = 5;
		logger.debug("from tlv:(tag=" + this.getTag() + ", child=" + childCount + ") to command");
		TlvParser.decodeChildren(tlv, childCount);

		account = new AccountDetailInfo();
		int i = 0;

		TlvObject tAccountName = tlv.getChild(i++);
		account.setName(new String(tAccountName.getValue(), "UTF-8"));

		TlvObject tPhone = tlv.getChild(i++);
		account.setTelephone(new String(tPhone.getValue(), "UTF-8"));

		TlvObject tDescription = tlv.getChild(i++);
		account.setDesc(new String(tDescription.getValue(), "UTF-8"));

		TlvObject tPhoto = tlv.getChild(i++);
		account.setImageUrl(new String(tPhoto.getValue(), "UTF-8"));

		TlvObject tTimestamp = tlv.getChild(i++);
		currentTimestamp = TlvByteUtil.byte2Int(tTimestamp.getValue());
		logger.debug("currentTimestamp: " + currentTimestamp);

		return this;
	}

	private AccountDetailInfo account;
	private int currentTimestamp;

	public void setAccount(AccountDetailInfo account)
	{
		this.account = account;
	}

	public AccountDetailInfo getAccount()
	{
		return account;
	}

	public int getCurrentTimestamp()
	{
		return currentTimestamp;
	}

	public void setCurrentTimestamp(int currentTimestamp)
	{
		this.currentTimestamp = currentTimestamp;
	}

	private final static Logger logger = LoggerFactory.getLogger(SyncMyAccountResp.class);
}
