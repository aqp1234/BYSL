<%@page import="java.util.List"%>
<%@page import="com.kms.bysl.dto.StudentBookCommentDTO"%>
<%@page import="com.kms.bysl.dto.SoloWorkspaceDTO"%>
<%@page import="com.kms.bysl.dto.StudentBookDTO"%>
<%@page import="com.kms.bysl.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDTO member = (MemberDTO) session.getAttribute("member");
	StudentBookDTO studentBook = (StudentBookDTO) request.getAttribute("studentBook");
	SoloWorkspaceDTO soloWorkspace = (SoloWorkspaceDTO) session.getAttribute("soloWorkspace");
	List<StudentBookCommentDTO> comments = (List<StudentBookCommentDTO>) request.getAttribute("comments");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentBook</title>
<link rel="stylesheet" type="text/css" href="/bysl/resources/css/studentBook.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/templates/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/leftSideBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/templates/soloWorkspaceBar.jsp"></jsp:include>
    <section id="main" class="workspace_section">
    	<% if (studentBook.getOwnerId() == member.getId()) { %>
		<div id="top_detail">
		    <a href='/bysl/solo/studentbook/<%= soloWorkspace.getId() %>' id='exit-btn'>◀</a>
		    <div style="display: flex;">
		        <span class="text_deco11_student"><a href='#' onclick="openInvitePage()" class="text_deco12" id="staffinvitebtn" data-type="studentbook">글 공유</a></span>
		        <span class="text_deco11_student"><a href='#' onclick="deletethis()" class="text_deco12">글 삭제</a></span>
		        <span class="text_deco11_student"><a href='/bysl/solo/studentbook/<%= soloWorkspace.getId() %>/update/<%= studentBook.getId() %>' class="text_deco12">글 수정</a></span>
		    </div>
		</div>
		<% } %>
		
		<div id="detail_top">
		    <div class="detail_title_studentbook">${studentBook.subject}</div>
		</div>
		
		<div readonly id="detail_body">${studentBook.content}</div>
		<div style="margin-left: 10px;"><hr size="3" noshade color="FDECB8" style="margin-top: 20px;"/></div>
		
		<div id="comment_box">
	    <div class="comments">Comments</div>
	        <div class="mt-3 border-bottom">
	            <% for (StudentBookCommentDTO comment : comments) { %>
	                <div class="border-top pt-1 pb-1">
	                    <% if (comment.isDeleted()) { %>
	                    <div class="row">
		                    <div class="user_who_send_comment">
	                            <div><%= comment.getCommenter() %></div>
	                        </div>
	                        <div class="col-9 col-md-10 col-lg-11" style="width: 100%;">
	                            <div class="comment-show" id="comment-show">
	                                <div class="comment" id="comment_<%= comment.getId() %>">
	                        			<div class="comment-comment-mb-3">(Deleted Comment)</div>
                        			</div>
                       			</div>
                   			</div>
               			</div>
	                    <% } else { %>
	                    <div class="row">
	                        <div class="user_who_send_comment">
	                            <div><%= comment.getCommenter() %></div>
	                        </div>
	                        <div class="col-9 col-md-10 col-lg-11" style="width: 100%;">
	                            <div class="comment-show" id="comment-show">
	                                <div class="comment" id="comment_<%= comment.getId() %>">
	                                    <div class="comment-comment-mb-3">
	                                        <%= comment.getComment() %>
	                                    </div>
	                                    <div style="display: flex; margin-top: 5px; align-items: center;">
	                                        <small class="d-block">
	                                            (Created: <span class="comment-createdAt"><%= comment.getCreatedAt() %></span>)
	                                            <% if (!comment.getCreatedAt().equals(comment.getUpdatedAt())) { %>
	                                            (Updated: <span class="comment-updatedAt"><%= comment.getUpdatedAt() %></span>)
	                                            <% } %>
	                                        </small>
	                                        <br>
	                                        <% if (comment.getCommenterId() == member.getId()) { %>
	                                        <small class="d-block-btn">
	                                            <a href="#comment_edit" onclick="editCommentbtn(<%= comment.getId() %>)" style="text-decoration: none;">✏</a>
	                                            |
	                                            <form style="display: inline" action="" method="DELETE">
	                                                <a href="#delete_comment_form" onclick="deleteComment(<%= comment.getId() %>)" style="text-decoration: none;">✖</a>
	                                            </form>
	                                        </small>
	                                    	<% } %>
	                                    </div>
	                                </div>
	                                <div class="comment_edit" id="comment_edit_<%= comment.getId() %>" style="display: none;">
	                                    <div class="mt-3">
	                                        <form action="" method="PUT" class="comment_edit_form" id="comment_edit_form_<%= comment.getId() %>">
	                                            <div class="row_comment_write_change">
	                                                <div class="col-8" style="width: 100%;">
	                                                    <textarea rows="2" class="form-control-change" name="comment" id="comment"><%= comment.getComment() %></textarea>
	                                                </div>
	                                                <div class="change_btn">
	                                                    <button type="button" class="btn btn-primary h-100 mr-2" id="edit-btn" data-id="<%= comment.getId() %>" data-comment_id="<%= comment.getId() %>" onclick="onEditComment(this)">Edit Comment</button>
	                                                    <a href="#comment_edit_cancel" id="cancel" onclick="editCommentCancelbtn(<%= comment.getId() %>)" style="margin-left: 10px;">Cancel ✖</a>
	                                                </div>
	                                            </div>
	                                        </form>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <% } %>
	                </div>
	            <% } %>
	        </div>
        </div>
	    <div class="mt-3">
	        <form action="comment" method="post" id="comment_form">
	            <div class="row_comment_write">
	                <div class="col-8" style="width: 100%;">
	                    <textarea rows="2" class="form-control" name="comment" id="comment"></textarea>
	                </div>
	                <div class="col-4">
	                    <button type="button" class="plus_button" onclick="onSubmit()">Add Comment</button>
	                </div>
	            </div>
	        </form>
	    </div>
    </section>
    <script src="/bysl/resources/javascript/studentBook/detail.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/ko.min.js"></script>
	<script>
	    const createdAt = document.getElementsByClassName("comment-createdAt");
	    for(i = 0; i < createdAt.length; i++){
	        createdAt[i].textContent = moment(createdAt[i].textContent).format('YYYY년 MM월 DD일 hh시 mm분');
	    }
	    const updatedAt = document.getElementsByClassName("comment-updatedAt");
	    for(i = 0; i < updatedAt.length; i++){
	        updatedAt[i].textContent = moment(updatedAt[i].textContent).format('YYYY년 MM월 DD일 hh시 mm분');
	    }
	</script>
</body>
</html>