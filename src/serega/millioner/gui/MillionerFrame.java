package serega.millioner.gui;
import net.miginfocom.swing.MigLayout;
import serega.millioner.commons.GuiBuilder;
import serega.millioner.entity.Question;
import serega.millioner.util.FriendType;
import serega.millioner.util.GenerationRandomNumber;
import serega.millioner.util.QuestionLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class MillionerFrame extends JFrame {

    public static final Dimension FRAME_SIZE = new Dimension(671, 610);
    public static final Dimension BUTTON_SIZE = new Dimension(230, 60);

    public HelpPanel panelHelp;
    public QuestionRegionPanel panelQuestionRegion;
    public QuestionProgressPanel panelQuestionProgress;
    public AnswerAreaPanel panelAnswerArea;

    private final Iterator<Question> iterator;

    // TODO: Сделать деньги!
    public static long winningMoney = 0;

    private Question currentQuestion;

    private boolean errorChance = false;

    GenerationRandomNumber generationRandomNumber = new GenerationRandomNumber();


    public MillionerFrame() {

        iterator = new QuestionLoader().getQuestionArray().iterator();

        currentQuestion = iterator.next();

        panelHelp = new HelpPanel();
        panelQuestionRegion = new QuestionRegionPanel();
        panelQuestionProgress = new QuestionProgressPanel();
        panelAnswerArea = new AnswerAreaPanel();

      //  panelAnswerArea.setBackground(new Color(255, 255, 255));

        setSize(new Dimension(FRAME_SIZE));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new MigLayout());

        add(panelHelp);
        add(panelQuestionRegion);
        add(panelQuestionProgress, "wrap");
        add(panelAnswerArea, "span");

        setVisible(true);

    }

    class HelpPanel extends JPanel {

        public final Dimension PANEL_HELP_SIZE = new Dimension(150, 400);
        public final Dimension BUTTON_HELP_SIZE = new Dimension(72, 72);

        public HelpPanel() {

            GuiBuilder.setComponentSize(this, PANEL_HELP_SIZE);

            JButton buttonTelephone = new JButton();
            JButton buttonAuditory = new JButton();
            JButton buttonHalf = new JButton();
            JButton buttonChange = new JButton();
            JButton buttonErrorChance = new JButton();

            GuiBuilder.setComponentSize(buttonTelephone, BUTTON_HELP_SIZE);
            GuiBuilder.setComponentSize(buttonAuditory, BUTTON_HELP_SIZE);
            GuiBuilder.setComponentSize(buttonHalf, BUTTON_HELP_SIZE);
            GuiBuilder.setComponentSize(buttonChange, BUTTON_HELP_SIZE);
            GuiBuilder.setComponentSize(buttonErrorChance, BUTTON_HELP_SIZE);

            buttonHalf.add(
                    new JLabel(
                            GuiBuilder.getScaledImageIcon(
                                    new ImageIcon(getClass().getResource(
                                            "/icons/half.png")),50,50)
                    )
            );

            buttonTelephone.add(
                    new JLabel(
                            GuiBuilder.getScaledImageIcon(
                                    new ImageIcon(getClass().getResource(
                                            "/icons/telephone.png")),50,50)
                    )
            );

            buttonAuditory.add(
                    new JLabel(
                            GuiBuilder.getScaledImageIcon(
                                    new ImageIcon(getClass().getResource(
                                            "/icons/people.png")),50,50)
                    )
            );

            buttonChange.add(
                    new JLabel(
                            GuiBuilder.getScaledImageIcon(
                                    new ImageIcon(getClass().getResource(
                                            "/icons/change.png")),50,50)
                    )
            );

            buttonErrorChance.add(
                    new JLabel(
                            GuiBuilder.getScaledImageIcon(
                                    new ImageIcon(getClass().getResource(
                                            "/icons/error.png")),50,50)
                    )
            );

            add(buttonTelephone);
            add(buttonAuditory);
            add(buttonHalf);
            add(buttonChange);
            add(buttonErrorChance);

            buttonHalf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int count = 0;

                    for (int i = 0; i < currentQuestion.getMasAnswer().length; i++) {
                        if (count < 2) {
                            if (!currentQuestion.getMasAnswer()[i].equals(currentQuestion.getTrueAnswer())) {
                                currentQuestion.getMasAnswer()[i] = "";
                                count++;
                            }
                        }
                    }

                    panelAnswerArea.feelButtonText();
                    panelAnswerArea.revalidate();
                    panelAnswerArea.repaint();
                    buttonHalf.setEnabled(false);

                }
            });

            buttonAuditory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JDialog dialog = new JDialog();
                    dialog.setLayout(new MigLayout());

                    dialog.setSize(190, 70);

                    ArrayList<Integer> random = generationRandomNumber.generateNumberGroup();

                    dialog.add(new JLabel("A: " + random.get(0) + "%"));
                    dialog.add(new JLabel("B: " + random.get(1) + "%"));
                    dialog.add(new JLabel("C: " + random.get(2) + "%"));
                    dialog.add(new JLabel("D: " + random.get(3) + "%"));
                    dialog.setVisible(true);

                    panelAnswerArea.feelButtonText();
                    panelAnswerArea.revalidate();
                    panelAnswerArea.repaint();
                    buttonAuditory.setEnabled(false);

                }
            });

            buttonTelephone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JDialog dialog = new JDialog();

                    FriendType friendType = FriendType.getRandomFriend();

                    if(friendType.getTrueAnswerForFriend()){
                        dialog.add(new JLabel( friendType.getTextPerson() + currentQuestion.getTrueAnswer()));
                    }else {
                        dialog.add(new JLabel(friendType.getTextPerson() + currentQuestion.wrongAnswer()));
                    }

                    dialog.setSize(500,100);
                    dialog.setVisible(true);

                    panelAnswerArea.revalidate();
                    panelAnswerArea.repaint();
                    buttonTelephone.setEnabled(false);
                }
            });

            buttonErrorChance.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errorChance=true;
                    buttonErrorChance.setEnabled(false);
                    panelHelp.revalidate();
                    panelHelp.repaint();
                }
            });

            buttonChange.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    QuestionLoader questionLoader = new QuestionLoader();
                    currentQuestion = questionLoader.getReservQuestion().get(0);
                    panelAnswerArea.feelButtonText();
                    panelAnswerArea.repaintPanelQuestion();
                    buttonChange.setEnabled(false);
                }
            });
        }
    }

    class QuestionRegionPanel extends JPanel {

        public final Dimension PANEL_QUESTION_REGION_SIZE = new Dimension(330, 400);

        public QuestionRegionPanel() {
            setLayout(new MigLayout());
            GuiBuilder.setComponentSize(this, PANEL_QUESTION_REGION_SIZE);
            add(new JLabel(currentQuestion.getTextQuestion()),"gaptop 162, gapleft 50");

        }

    }

    class QuestionProgressPanel extends JPanel {

        public final Dimension PANEL_PROGRESS_SIZE = new Dimension(150, 400);
        public final Dimension JLABLE_PROGRESS_SIZE = new Dimension(150, 30);

        Iterator<JLabel> progressIterator;


        public ArrayList<JLabel> winProgress = new ArrayList<>();


        public QuestionProgressPanel() {
            setLayout(new MigLayout("wrap 1"));

            GuiBuilder.setComponentSize(this, PANEL_PROGRESS_SIZE);

            ArrayList<Long> progressGetData = GuiBuilder.progressSimvols();
            ArrayList<JLabel> progressImage = new ArrayList<>();


            for (int i = progressGetData.size()-1; i >= 0; i--) {

                JLabel label = new JLabel(i + 1 + ")               " + progressGetData.get(i));
                GuiBuilder.setComponentSize(label,JLABLE_PROGRESS_SIZE);
                label.setOpaque(true);

                progressImage.add(label);
                add(label);
            }

            for (int i = progressImage.size()-1; i >=0 ; i--) {
                winProgress.add(progressImage.get(i));
            }

            progressIterator = winProgress.iterator();

        }

        public void progressUp(){

            JLabel label = progressIterator.next();
//            int a = Integer.parseInt(label.getText());
//            System.out.println(a);
            label.setBackground(new Color(68, 156, 40));
            label.revalidate();
            label.repaint();
            label.updateUI();
            this.revalidate();
            this.repaint();
        }

        public void progressBreak(){

            JLabel label = progressIterator.next();
            label.setBackground(new Color(222, 16, 33));
            label.revalidate();
            label.repaint();
            label.updateUI();
            this.revalidate();
            this.repaint();
        }

    }

     class AnswerAreaPanel extends JPanel {

        public final Dimension PANEL_ANSWER_SIZE = new Dimension(630, 200);

        private final JButton buttonVariantA;
        private final JButton buttonVariantB;
        private final JButton buttonVariantC;
        private final JButton buttonVariantD;

        public AnswerAreaPanel() {

            GuiBuilder.setComponentSize(this, PANEL_ANSWER_SIZE);

            buttonVariantA = createButtonAnswer();
            buttonVariantB = createButtonAnswer();
            buttonVariantC = createButtonAnswer();
            buttonVariantD = createButtonAnswer();

            add(buttonVariantA);
            add(buttonVariantB);
            add(buttonVariantC);
            add(buttonVariantD);

            feelButtonText();

        }

        public JButton createButtonAnswer() {

            JButton button = new JButton();

          //  button.setBackground(new Color(246, 188, 188));
            GuiBuilder.setComponentSize(button, BUTTON_SIZE);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (!iterator.hasNext()) {
                        System.exit(1);

                    } else {

                        if (currentQuestion.checkAnswer(button.getActionCommand())) {

                            currentQuestion = iterator.next();
                            feelButtonText();
                            repaintPanelQuestion();
                            panelQuestionProgress.progressUp();

                        } else {
                            if(errorChance) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Неправильный ответ, есть вторая попытка",
                                        "Вторая попытка!",
                                        JOptionPane.ERROR_MESSAGE
                                );

                                button.setText("");
                                errorChance = false;

                            } else {
                                panelQuestionProgress.progressBreak();
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Неправильный ответ, игре конец",
                                        "Конец!",
                                        JOptionPane.ERROR_MESSAGE
                                );

                                System.exit(1);
                            }
                        }
                    }

                }
            });
            return button;
        }

        public void feelButtonText() {
            ArrayList<Integer> listNumbers = generationRandomNumber.generateNumberHalf();
            buttonVariantA.setText(currentQuestion.getMasAnswer()[listNumbers.get(0)]);
            buttonVariantB.setText(currentQuestion.getMasAnswer()[listNumbers.get(1)]);
            buttonVariantC.setText(currentQuestion.getMasAnswer()[listNumbers.get(2)]);
            buttonVariantD.setText(currentQuestion.getMasAnswer()[listNumbers.get(3)]);
        }

        public void repaintPanelQuestion() {
            panelQuestionRegion.removeAll();
            panelQuestionRegion.add(new JLabel(currentQuestion.getTextQuestion()),"gaptop 162, gapleft 50");

            panelQuestionRegion.revalidate();
            panelQuestionRegion.repaint();
        }
    }


}



