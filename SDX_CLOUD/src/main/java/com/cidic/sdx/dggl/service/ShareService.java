package com.cidic.sdx.dggl.service;

import java.util.List;

import com.cidic.sdx.dggl.model.Share;

public interface ShareService {

	public int createShare(Share share);
	
	public List<Share> getShareList(int matchId, int userId);
}
