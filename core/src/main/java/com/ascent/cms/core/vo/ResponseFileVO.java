package com.ascent.cms.core.vo;

import java.io.Serializable;

public class ResponseFileVO  implements Serializable{
		 private String massage;
		 private String filePath;
		 private Long id;
		public String getMassage() {
			return massage;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public void setMassage(String massage) {
			this.massage = massage;
		}
	
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		 
		 
		
	}


