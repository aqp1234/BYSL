package com.kms.bysl.service;

import java.util.List;

import com.kms.bysl.dto.ConferenceDTO;

public interface ConferenceService {
	public void conferenceInsert(ConferenceDTO conference, int ownerUserWorkspaceId);
	public List<ConferenceDTO> conferenceSelectByWorkspaceId(int workspaceId);
	public ConferenceDTO conferenceSelectByConferenceId(int conferenceId);
	public void conferenceUpdate(ConferenceDTO conference);
	public void conferenceDelete(int conferenceId);
}
