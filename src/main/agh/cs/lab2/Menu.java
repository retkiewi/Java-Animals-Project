package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Menu implements ActionListener {
    Random random = new Random();
    public boolean isPaused = true;
    public boolean isStopped = true;
    Integer xSize;
    Integer ySize;
    Integer jungleSize;
    Integer grassEnergy;
    Integer animalMaxEnergy;
    Integer numberOfAnimals;
    Integer animalStartingEnergy;
    JTextField xSizeField;
    JTextField ySizeField;
    JTextField jungleSizeField;
    JTextField grassEnergyField;
    JTextField animalMaxEnergyField;
    JTextField numberOfAnimalsField;
    JTextField animalStartingEnergyField;
    JLabel xSizeLabel;
    JLabel ySizeLabel;
    JLabel jungleSizeLabel;
    JLabel grassEnergyLabel;
    JLabel animalMaxEnergyLabel;
    JLabel numberOfAnimalsLabel;
    JLabel animalStartingEnergyLabel;
    LoopingMap projectSpace;
    AnimalStatistics statistics;
    AnimalSelector selector;
    Menu(Integer args[]){
        JFrame menu = new JFrame("Menu");
        Box horizontalBox = Box.createHorizontalBox();
        JPanel mainMenu = new JPanel();
        menu.add(horizontalBox);
        horizontalBox.add(mainMenu);
        menu.setSize(new Dimension(1350, 600));
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LABELS
        xSizeLabel = new JLabel("X size:              ");
        ySizeLabel = new JLabel("Y size:              ");
        jungleSizeLabel = new JLabel("Jungle size:              ");
        grassEnergyLabel = new JLabel("Grass energy:              ");
        animalMaxEnergyLabel = new JLabel("Animal max energy:              ");
        numberOfAnimalsLabel = new JLabel("Number of animals:              ");
        animalStartingEnergyLabel = new JLabel("Animal starting energy:              ");
        
        //LAYOUT
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));

        //PARAMETERS
        int a = 15;

        xSizeField = new JTextField(args[0].toString(), a);
        xSizeLabel.setLabelFor(xSizeField);
        JPanel xSizePanel = new JPanel();
        mainMenu.add(xSizePanel);
        xSizePanel.add(xSizeLabel);
        xSizePanel.add(xSizeField);

        ySizeField = new JTextField(args[1].toString(), a);
        ySizeLabel.setLabelFor(ySizeField);
        JPanel ySizePanel = new JPanel();
        mainMenu.add(ySizePanel);
        ySizePanel.add(ySizeLabel);
        ySizePanel.add(ySizeField);

        jungleSizeField = new JTextField(args[2].toString(), a);
        jungleSizeLabel.setLabelFor(jungleSizeField);
        JPanel jungleSizePanel = new JPanel();
        mainMenu.add(jungleSizePanel);
        jungleSizePanel.add(jungleSizeLabel);
        jungleSizePanel.add(jungleSizeField);

        grassEnergyField = new JTextField(args[3].toString(), a);
        grassEnergyLabel.setLabelFor(grassEnergyField);
        JPanel grassEnergyPanel = new JPanel();
        mainMenu.add(grassEnergyPanel);
        grassEnergyPanel.add(grassEnergyLabel);
        grassEnergyPanel.add(grassEnergyField);

        animalMaxEnergyField = new JTextField(args[4].toString(), a);
        animalMaxEnergyLabel.setLabelFor(animalMaxEnergyField);
        JPanel animalMaxEnergyPanel = new JPanel();
        mainMenu.add(animalMaxEnergyPanel);
        animalMaxEnergyPanel.add(animalMaxEnergyLabel);
        animalMaxEnergyPanel.add(animalMaxEnergyField);

        animalStartingEnergyField = new JTextField(args[5].toString(), a);
        animalStartingEnergyLabel.setLabelFor(animalStartingEnergyField);
        JPanel animalStartingEnergyPanel = new JPanel();
        mainMenu.add(animalStartingEnergyPanel);
        animalStartingEnergyPanel.add(animalStartingEnergyLabel);
        animalStartingEnergyPanel.add(animalStartingEnergyField);

        numberOfAnimalsField = new JTextField(args[6].toString(), a);
        numberOfAnimalsLabel.setLabelFor(numberOfAnimalsField);
        JPanel numberOfAnimalsPanel = new JPanel();
        mainMenu.add(numberOfAnimalsPanel);
        numberOfAnimalsPanel.add(numberOfAnimalsLabel);
        numberOfAnimalsPanel.add(numberOfAnimalsField);

        //BUTTONS
        JButton pause = new JButton("Unpause");
        pause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isPaused){
                    pause.setText("Pause");
                    isPaused=false;
                }
                else {
                    pause.setText("Unpause");
                    isPaused=true;
                }
            }

        });
        pause.setSize(100,20);
        pause.setEnabled(false);
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isStopped){
                    start.setText("Stop");
                    isStopped=false;

                    xSize = Integer.valueOf(xSizeField.getText());
                    ySize = Integer.valueOf(ySizeField.getText());
                    jungleSize = Integer.valueOf(jungleSizeField.getText());
                    grassEnergy = Integer.valueOf(grassEnergyField.getText());
                    animalMaxEnergy = Integer.valueOf(animalMaxEnergyField.getText());
                    numberOfAnimals = Integer.valueOf(numberOfAnimalsField.getText());
                    animalStartingEnergy = Integer.valueOf(animalStartingEnergyField.getText());
                    projectSpace = new LoopingMap(xSize, ySize, jungleSize, grassEnergy, animalMaxEnergy);
                    statistics = new AnimalStatistics(projectSpace);
                    selector = new AnimalSelector(projectSpace);
                    horizontalBox.add(selector);
                    horizontalBox.add(statistics);

                    for(int i=0; i<numberOfAnimals; i++){
                        new EvolvingAnimal(new Vector2d(random.nextInt(xSize), random.nextInt(ySize)), TurningDirection.NORTH.turn(random.nextInt(8)), animalStartingEnergy, projectSpace);
                    }
                    pause.setEnabled(true);
                }
                else {
                    pause.setText("Unpause");
                    isPaused=true;
                    pause.setEnabled(false);
                    start.setText("Start");
                    isStopped=true;
                    projectSpace.visualizer.f.dispose();
                    horizontalBox.remove(statistics);
                    horizontalBox.remove(selector);
                    projectSpace=null;
                }
            }

        });
        start.setSize(100,20);
        mainMenu.add(start);
        

        mainMenu.add(pause);
        mainMenu.setSize(new Dimension(400, 600));

        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
