package pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.StringUtil;

public class WbContentInfoPojo {
	private long id;
	private String content;
	private Date Time;
	private int repostsCount;
	private int commentsCount;
	
	public String toString4FileOutput() {
		List<Object> fieldList = new ArrayList<>();
		fieldList.add(id);
		fieldList.add(content);
		fieldList.add(Time);
		fieldList.add(repostsCount);
		fieldList.add(commentsCount);
		
		return StringUtil.join(fieldList, "\001");
	}
	
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
