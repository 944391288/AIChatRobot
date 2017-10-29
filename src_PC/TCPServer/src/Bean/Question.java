package Bean;

public class Question {
    private int id=-1;
    private String cate="";
    private String key="";
    private String answer="";

    public Question() {
    }

    public Question(int id, String cate, String key, String answer) {
        this.id = id;
        this.cate = cate;
        this.key = key;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
