package com.kms.bysl.dao;

import java.util.List;

import com.kms.bysl.dto.ConferenceDTO;

public interface ConferenceDAO {
	public void conferenceInsert(ConferenceDTO conference, int ownerUserWorkspaceId);
	public List<ConferenceDTO> conferenceSelectByWorkspaceId(int workspaceId);
	public List<ConferenceDTO> conferenceSelectByConferenceId(int conferenceId);
	public void conferenceUpdate(ConferenceDTO conference);
	public void conferenceDelete(int conferenceId);
}
