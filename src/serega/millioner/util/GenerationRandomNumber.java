package serega.millioner.util;

import serega.millioner.entity.Question;

import javax.swing.*;
import java.util.ArrayList;

public class GenerationRandomNumber {

    public ArrayList<Integer> generateNumberHalf(){
        ArrayList<Integer> randomNumbersAnswer = new ArrayList<Integer>();

        while (randomNumbersAnswer.size() != 4){
            int a = (int) (Math.random() * 4);
            if(!randomNumbersAnswer.contains(a)){
                randomNumbersAnswer.add(a);
            }
        }

        return randomNumbersAnswer;
    }

    public ArrayList<Integer> generateNumberGroup() {
        System.err.println(1);
        ArrayList<Integer> randomNumbersGroup = new ArrayList<Integer>();

        int parsent = 100;

        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * parsent);
            parsent -= random;
            System.out.println(random);
            randomNumbersGroup.add(random);
        }
        randomNumbersGroup.add(parsent);

        return randomNumbersGroup;
    }

    public String getTrueAnswerForFriend(FriendType friendType,Question question){
        String resultText="";
        switch (friendType){

            case GENIUS_FRIEND -> resultText = question.getTrueAnswer();

            case BULL_FRIEND -> {
                int a = (int) (Math.random()*100);
                if(a<=10){
                    resultText = question.getTrueAnswer();
                } else {
                   resultText = question.wrongAnswer();
                }
            }
        }
        return resultText;
    }

}
