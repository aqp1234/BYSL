package com.kms.bysl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.bysl.dao.ConferenceDAO;
import com.kms.bysl.dto.ConferenceDTO;
import com.kms.bysl.exception.NullObjectException;

@Service
public class ConferenceServiceImpl implements ConferenceService{

	@Autowired
	private ConferenceDAO dao;
	
	@Override
	public void conferenceInsert(ConferenceDTO conference, int ownerUserWorkspaceId) {
		dao.conferenceInsert(conference, ownerUserWorkspaceId);
	}

	@Override
	public List<ConferenceDTO> conferenceSelectByWorkspaceId(int workspaceId) {
		List<ConferenceDTO> conferences;
		
		conferences = dao.conferenceSelectByWorkspaceId(workspaceId);
		
		return conferences;
	}

	@Override
	public ConferenceDTO conferenceSelectByConferenceId(int conferenceId) {
		List<ConferenceDTO> conferences;
		
		conferences = dao.conferenceSelectByConferenceId(conferenceId);
		
		if(conferences.size() == 0) {
			throw new NullObjectException(new Exception(), "없거나 삭제된 회의록입니다.");
		}
		
		return conferences.get(0);
	}

	@Override
	public void conferenceUpdate(ConferenceDTO conference) {
		dao.conferenceUpdate(conference);
	}

	@Override
	public void conferenceDelete(int conferenceId) {
		dao.conferenceDelete(conferenceId);
	}

}
