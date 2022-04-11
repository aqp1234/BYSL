package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.SoloShareDTO;

public interface SoloShareService {
	public void soloShareInsert(SoloShareDTO soloShare);
	public List<SoloShareDTO> soloShareSelect(int ownerId, String url);
	public List<SoloShareDTO> soloShareSelectAllByManagerId(int ownerId);
	public SoloShareDTO soloShareSelectByManagerId(int ownerId, String url);
	public void soloShareDelete(int soloShareId);
}
