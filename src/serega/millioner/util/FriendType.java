package serega.millioner.util;

    // TODO: Нужно запретить доступ на прямую к переменным из вне. Переменные должны быть не изменяемыми
    public enum FriendType {

        BULL_FRIEND("Вечер в хату, слыш, кароч, ну это, эт кажись ", 10),
        GENIUS_FRIEND("Доброго дня, мой дорогой друг, довольно легкий вопрос... Это ", 100),
        STANDART_FRIEND("Привет! Хмммм... Интересный вопрос... Я думаю, что это ", 50),
        FAMILIAR_FRIEND("Здаров бро! Таааакс, озадачил ты меня. Наверное это ", 70),
        INTELLECTUAL_FRIEND("Здравствуй дорогой друг, слушай, честно не знаю... Наверное это ", 55);

        String textPerson;
        int parcentTrueAnswer;

        FriendType(String textPerson, int parcentTrueAnswer) {
            this.textPerson = textPerson;
            this.parcentTrueAnswer = parcentTrueAnswer;
        }

        public static FriendType getRandomFriend(){
            int randomNumber = (int) (Math.random()*4);
            return FriendType.values()[randomNumber];
        }

        public String getTextPerson() {
            return textPerson;
        }

        public int getParcentTrueAnswer() {
            return parcentTrueAnswer;
        }

        public boolean getTrueAnswerForFriend(){
            int a =(int)(Math.random() * 100);
            System.out.println(a + " " + getParcentTrueAnswer());
            return a <= getParcentTrueAnswer();
        }
    }

