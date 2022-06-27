package pl.pjatk.memorygame.Model;

public class RecordScore {
    private String name;
    private String time;
    private int width;
    private int height;
    private int score;

    public RecordScore(String name, String time, int width, int height) {
        this.name = name;
        this.time = time;
        this.width = width;
        this.height = height;
        this.score = calculateScore();
    }

    private int calculateScore(){
        String[] timeArr = time.split(":");
        int seconds = Integer.parseInt(timeArr[0])*60 + Integer.parseInt(timeArr[1]);
        return this.width*this.height*100 / seconds;
    }

    @Override
    public String toString(){
        return this.name + " (Time: " + this.time + ", grid " + this.width + "x" + this.height + ")";
    }
}
