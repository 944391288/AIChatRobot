package Bean;

public class Ask {
    private int cid=-1;
    private int qid=-1;
    private String time="";

    public Ask(int cid, int qid, String time) {
        this.cid = cid;
        this.qid = qid;
        this.time = time;
    }

    public Ask() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
