package com.kms.bysl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.MemberDTO;
import com.kms.bysl.dto.SchoolRecordDTO;
import com.kms.bysl.dto.SchoolRecordFileDTO;
import com.kms.bysl.dto.ShareFileDTO;
import com.kms.bysl.service.SchoolRecordFileService;
import com.kms.bysl.service.SchoolRecordService;

@Controller
@RequestMapping(value="/solo/schoolRecord")
public class SchoolRecordController {
	
	@Autowired
	String uploadPath;
	
	@Autowired
	private SchoolRecordService schoolRecordService;
	
	@Autowired
	private SchoolRecordFileService fileService;
	
	@RequestMapping(value="/{soloWorkspaceId}", method=RequestMethod.GET)
	public String schoolRecordList(@PathVariable int soloWorkspaceId, HttpServletRequest request) {
		List<SchoolRecordDTO> schoolRecords = schoolRecordService.schoolRecordSelectBySoloWorkspaceId(soloWorkspaceId);
		
		request.setAttribute("schoolRecords", schoolRecords);
		return "schoolRecord/main";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.GET)
	public String schoolRecordAddForm() {
		return "schoolRecord/add";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> schoolRecordAdd(@PathVariable int soloWorkspaceId, SchoolRecordDTO schoolRecord,
			HttpSession session, MultipartFile[] files, HttpServletRequest request) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		schoolRecord.setSoloWorkspaceId(soloWorkspaceId);
		schoolRecord.setOwnerId(member.getId());
		schoolRecord.setName(member.getName());
		
		int schoolRecordId = schoolRecordService.schoolRecordInsert(schoolRecord);
		
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			if(fileName == "") {
				continue;
			}
			UUID uuid = UUID.randomUUID();
			String uuidName = uuid + "_" + file.getOriginalFilename();
			File target = new File(uploadPath, uuidName);
	        if ( ! new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        try {
	            FileCopyUtils.copy(file.getBytes(), target);
	        	SchoolRecordFileDTO exfile = new SchoolRecordFileDTO();
	        	exfile.setSchoolRecordId(schoolRecordId);
	        	exfile.setName(uuidName);
	        	exfile.setPath(uploadPath + "\\" + uuidName);
	        	exfile.setOriginalName(fileName);
	            fileService.schoolRecordFileInsert(exfile);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		}
		
		ResponseData responseData = new ResponseData();
		responseData.setMessage("생활기록부 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK, "/bysl/solo/schoolRecord/" + soloWorkspaceId);
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/{schoolRecordId}", method=RequestMethod.GET)
	public String schoolRecordDetail(@PathVariable int schoolRecordId, HttpServletRequest request) {
		SchoolRecordDTO schoolRecord = schoolRecordService.schoolRecordSelectBySchoolRecordId(schoolRecordId);
		List<SchoolRecordFileDTO> files = fileService.schoolRecordFileSelectBySchoolRecordId(schoolRecordId);
		
		request.setAttribute("schoolRecord", schoolRecord);
		request.setAttribute("files", files);
		return "schoolRecord/detail";
	}
	
	@RequestMapping(value="/{soloWorkspaceId}/update/{schoolRecordId}", method=RequestMethod.GET)
	public String schoolRecordUpdateForm(@PathVariable int schoolRecordId, HttpServletRequest request) {
		SchoolRecordDTO schoolRecord = schoolRecordService.schoolRecordSelectBySchoolRecordId(schoolRecordId);
		List<SchoolRecordFileDTO> files = fileService.schoolRecordFileSelectBySchoolRecordId(schoolRecordId);
		
		request.setAttribute("schoolRecord", schoolRecord);
		request.setAttribute("files", files);
		return "schoolRecord/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/update/{schoolRecordId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> schoolRecordUpdate(SchoolRecordDTO schoolRecord, @RequestParam(value = "deletefile", required = false, defaultValue = "") int[] deleteIds
			, MultipartFile[] files, @PathVariable int schoolRecordId) {
		schoolRecordService.schoolRecordUpdate(schoolRecord);
		for(int deleteId : deleteIds) {
			SchoolRecordFileDTO exfile = fileService.schoolRecordFileSelectByFileId(deleteId);
			File file = new File(exfile.getPath());
			file.delete();
			fileService.schoolRecordFileDelete(deleteId);
		}
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			if(fileName == "") {
				continue;
			}
			UUID uuid = UUID.randomUUID();
			String uuidName = uuid + "_" + file.getOriginalFilename();
			File target = new File(uploadPath, uuidName);
	        if ( ! new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        try {
	            FileCopyUtils.copy(file.getBytes(), target);
	        	SchoolRecordFileDTO exfile = new SchoolRecordFileDTO();
	        	exfile.setSchoolRecordId(schoolRecordId);
	        	exfile.setName(uuidName);
	        	exfile.setPath(uploadPath + "\\" + uuidName);
	        	exfile.setOriginalName(fileName);
	            fileService.schoolRecordFileInsert(exfile);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		}

		ResponseData responseData = new ResponseData();
		responseData.setMessage("생활기록부 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{soloWorkspaceId}/{schoolRecordId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> schoolRecordDelete(@PathVariable int schoolRecordId) {
		schoolRecordService.schoolRecordDelete(schoolRecordId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("생활기록부 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/download/{fileId}", method=RequestMethod.GET)
	public void download(@PathVariable int fileId, HttpServletResponse response) throws Exception{
		try {
			SchoolRecordFileDTO exfile = fileService.schoolRecordFileSelectByFileId(fileId);
        	String path = exfile.getPath();
        	
        	File file = new File(path);
        	String filename = file.getName().split("_")[1];
        	filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        	response.setHeader("Content-Disposition", "attachment;filename=" + filename); 
        	
        	FileInputStream fileInputStream = new FileInputStream(path); 
        	OutputStream out = response.getOutputStream();
        	
        	int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) { 
                    out.write(buffer, 0, read);
                }
                
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
