package edu.skku.cs.pa3;

public class RecordData {
    private String name;
    private String score;
    private String date;

    public RecordData(String _name, int _score, String _date){
        name = _name;
        score = ""+_score;
        date = _date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
