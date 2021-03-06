package com.zhouhuan.common.utils;

import java.io.Serializable;
public class MetaInfo  implements Serializable{
	 private static final long serialVersionUID = -198875474009728992L;
	  private String UUID = null;
	  private String APPKEY = null;
	  private long FTIME = 0L;

	  public MetaInfo(String appkey) {
	    this.APPKEY = appkey;
	  }

	  public void init() {
	    if (this.APPKEY == null) {
	      this.APPKEY = "0000000000000";
	    }
	    this.FTIME = System.currentTimeMillis();
	    if (this.UUID == null) {
	      long id = Thread.currentThread().getId();
	      double math = Math.random();
	      this.UUID = ((math * this.FTIME) + "-" + id + "-" + (math * id * 10000000.0D));
	      if (this.UUID.length() > 22)
	        this.UUID = this.UUID.substring(0, 22);
	    }
	  }

	  public String toString()
	  {
	    return "Meta(" + getTIME() + "ms)";
	  }

	  public long getTIME() {
	    return System.currentTimeMillis() - this.FTIME;
	  }

	  public String getUUID() {
	    return this.UUID;
	  }

	  public String getAPPKEY() {
	    return this.APPKEY;
	  }
}
