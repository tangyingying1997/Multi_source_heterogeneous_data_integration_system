package pojos;

import java.util.Date;

public class WbContentInfoPojo {
	private long id;
	private String content;
	private Date Time;
	private int repostsCount;
	private int commentsCount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
	public int getRepostsCount() {
		return repostsCount;
	}
	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	
	
}
