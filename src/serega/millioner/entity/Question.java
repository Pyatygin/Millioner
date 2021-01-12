package serega.millioner.entity;
import java.util.Arrays;

public class Question {

    private String textQuestion = "";
    private String trueAnswer;
    private String[] masAnswer;

    public Question(String textQuestion, String trueAnswer, String[] masAnswer) {
        this.textQuestion = textQuestion;
        this.trueAnswer = trueAnswer;
        this.masAnswer = masAnswer;
    }

    public boolean checkAnswer(String answer) {
        return this.trueAnswer.equals(answer);
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }


    public String[] getMasAnswer() {
        return masAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "textQuestion='" + textQuestion + '\'' +
                ", trueAnswer=" + trueAnswer +
                ", masAnswer=" + Arrays.toString(masAnswer) +
                '}';
    }

    public String wrongAnswer(){
        String resultText="";
        while(true){
            int a = (int) (Math.random()*4);
            if(!getMasAnswer()[a].equals(getTrueAnswer())){
                resultText = getMasAnswer()[a];
                break;
            }
        }

        return resultText;
    }
}
