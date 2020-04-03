package pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.DateUtil;
import utils.StringUtil;

public class WbContentInfoPojo {
	private long id;
	private String content;
	private Date time;
	private int repostsCount;
	private int commentsCount;
	
	
	@Override
	public String toString() {
		return "WbContentInfoPojo [id=" + id + ", content=" + content + ", time=" + time + ", repostsCount="
				+ repostsCount + ", commentsCount=" + commentsCount + "]";
	}

	public String toString4FileOutput() {
		List<Object> fieldList = new ArrayList<>();
		fieldList.add(id);
		fieldList.add(content);
		//调用小工具修改下形式
		//fieldList.add(time);
		fieldList.add(DateUtil.formatDate(time));
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
		return time;
	}
	public void setTime(Date time) {
		//就因为这，给我搞了个空指针的异常，整了那么久才发现少了this.     time = time;
		this.time = time;
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
