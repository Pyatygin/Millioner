package serega.millioner.util;

import serega.millioner.entity.Question;

import java.util.ArrayList;

public class QuestionLoader {

    private ArrayList<Question> questionArray = new ArrayList();

    private ArrayList<Question> reservQuestion = new ArrayList();

    public QuestionLoader() {

        addQuestionInArray(
                "<html>Возле какой горы-вулкана был впервые <br> найден драгоценный камень танзанит?<html>",
                "Килиманджаро",
                "Чогори","Килиманджаро","Макалу","Эльбрус"
        );
        addQuestionInArray(
                "<html>Какой головной убор был во время бала на <br> Татьяне Лариной,  героине романа «Евгений Онегин»?<html>",
                "Малиновый берет",
                "Малиновый берет", "Канотье с чёрной лентой","Черный капор","Белая памела"
        );
        addQuestionInArray(
                "<html>Какой продукт в разных странах называют <br> папиной бородой  и бабушкиными волосами?<html>",
                "Сахарная вата",
                "Рамбутан","Макароны","Сахарная вата","Драконий фрукт"
        );
        addQuestionInArray(
                "<html>Как называется красная тряпка в руках у матадора?<html>",
                "Мулета",
                "Ляссе","Флаундер","Бандерилья","Мулета"
        );
//        addQuestionInArray(
//                "<html>Какое астрономическое явление <br> жители Земли  могут наблюдать раз в 75–76 лет?<html>",
//                "Комета Галлея",
//                "Светящиеся пляжи","Комета Галлея","Кольцо Бишопа","Гало"
//        );

        addQuestionInRezervArray(
                "<html>Какое астрономическое явление <br> жители Земли  могут наблюдать раз в 75–76 лет?<html>",
                "Комета Галлея",
                "Светящиеся пляжи","Комета Галлея","Кольцо Бишопа","Гало"
        );
    }

    public void addQuestionInArray(String textQuestion,String trueAnswer,String... masArray){
        questionArray.add(new Question(textQuestion,trueAnswer,masArray)
        );
    }

    public void addQuestionInRezervArray(String textQuestion,String trueAnswer,String... masArray){
        reservQuestion.add(new Question(textQuestion,trueAnswer,masArray)
        );
    }

    public ArrayList<Question> getQuestionArray() {
        return questionArray;
    }

    public ArrayList<Question> getReservQuestion() {
        return reservQuestion;
    }
}
