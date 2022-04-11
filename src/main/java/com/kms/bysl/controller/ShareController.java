package com.kms.bysl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

import com.kms.bysl.dto.ShareFileDTO;
import com.kms.bysl.ResponseData;
import com.kms.bysl.dto.ShareDTO;
import com.kms.bysl.dto.UserWorkspaceDTO;
import com.kms.bysl.service.ShareFileService;
import com.kms.bysl.service.ShareService;

@Controller
@RequestMapping(value="/share")
public class ShareController {
	
	@Autowired
	String uploadPath;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private ShareFileService shareFileService;

	@RequestMapping(value="/{workspaceId}", method=RequestMethod.GET)
	public String shareList(@PathVariable int workspaceId, HttpServletRequest request) {
		List<ShareDTO> shares;
		
		shares = shareService.shareSelectByWorkspaceId(workspaceId);
		
		request.setAttribute("shares", shares);
		return "share/main";
	}
	
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.GET)
	public String shareAddForm() {
		return "share/add";
	}

	
	@RequestMapping(value="/{workspaceId}/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> shareAdd(@PathVariable int workspaceId, HttpServletRequest request, HttpSession session, MultipartFile[] files) {
		UserWorkspaceDTO userWorkspace;
		ShareDTO share = new ShareDTO();
		userWorkspace = (UserWorkspaceDTO) session.getAttribute("userWorkspace");
		
		share.setWorkspaceId(workspaceId);
		share.setOwnerId(userWorkspace.getUserId());
		share.setNick(userWorkspace.getNick());
		share.setSubject(request.getParameter("subject"));
		share.setContent(request.getParameter("content"));
		int shareId = shareService.shareInsert(share, userWorkspace.getId());

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
	        	ShareFileDTO exfile = new ShareFileDTO();
	        	exfile.setShareId(shareId);
	        	exfile.setName(uuidName);
	        	exfile.setPath(uploadPath + "\\" + uuidName);
	        	exfile.setOriginalName(fileName);
	            shareFileService.fileInsert(exfile);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		}

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자료 공유 추가가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK, "/bysl/share/" + workspaceId);
	}
	
	@RequestMapping(value="/{workspaceId}/{shareId}", method=RequestMethod.GET)
	public String shareDetail(@PathVariable int shareId, HttpServletRequest request) {
		ShareDTO share;
		List<ShareFileDTO> files;
		
		share = shareService.shareSelectByShareId(shareId);
		files = shareFileService.fileSelectByShareId(shareId);
		
		request.setAttribute("share", share);
		request.setAttribute("files", files);
		return "share/detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/{shareId}", method=RequestMethod.DELETE)
	public ResponseEntity<ResponseData> shareDelete(@PathVariable int shareId) {
		shareService.shareDelete(shareId);

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자료 공유 삭제가 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{workspaceId}/update/{shareId}", method=RequestMethod.GET)
	public String shareUpdateForm(@PathVariable int shareId, HttpServletRequest request) {
		ShareDTO share = shareService.shareSelectByShareId(shareId);
		List<ShareFileDTO> files = shareFileService.fileSelectByShareId(shareId);
		
		request.setAttribute("share", share);
		request.setAttribute("files", files);
		return "share/update";
	}
	
	@ResponseBody
	@RequestMapping(value="/{workspaceId}/update/{shareId}", method=RequestMethod.POST)
	public ResponseEntity<ResponseData> shareUpdate(ShareDTO share, @RequestParam(value = "deletefile", required = false, defaultValue = "") int[] deleteIds
			, MultipartFile[] files, @PathVariable int shareId) {
		shareService.shareUpdate(share);
		for(int deleteId : deleteIds) {
			ShareFileDTO exfile = shareFileService.fileSelectByFileId(deleteId);
			File file = new File(exfile.getPath());
			file.delete();
			shareFileService.fileDelete(deleteId);
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
	        	ShareFileDTO exfile = new ShareFileDTO();
	        	exfile.setShareId(shareId);
	        	exfile.setName(uuidName);
	        	exfile.setPath(uploadPath + "\\" + uuidName);
	        	exfile.setOriginalName(fileName);
	            shareFileService.fileInsert(exfile);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		}

		ResponseData responseData = new ResponseData();
		responseData.setMessage("자료 공유 수정이 완료되었습니다.");
		return responseData.getResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="/download/{fileId}", method=RequestMethod.GET)
	public void download(@PathVariable int fileId, HttpServletResponse response) throws Exception{
		try {
			ShareFileDTO exfile = shareFileService.fileSelectByFileId(fileId);
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
